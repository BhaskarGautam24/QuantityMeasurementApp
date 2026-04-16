package com.quantitymeasurement.user.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.quantitymeasurement.user.dto.AuthResponse;
import com.quantitymeasurement.user.dto.GoogleAuthRequest;
import com.quantitymeasurement.user.dto.LoginRequest;
import com.quantitymeasurement.user.dto.RegisterRequest;
import com.quantitymeasurement.user.entity.UserEntity;
import com.quantitymeasurement.user.repository.UserRepository;
import com.quantitymeasurement.user.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${google.client-id}")
    private String googleClientId;

    // ─── Standard Register ───────────────────────────────────────────
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setProvider("LOCAL");
        userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
    }

    // ─── Standard Login ──────────────────────────────────────────────
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        UserEntity user = userRepository.findByEmail(request.getEmail()).orElse(null);
        if (user == null || user.getPassword() == null ||
                !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
    }

    // ─── Google OAuth2 Login / Register ─────────────────────────────
    @PostMapping("/google")
    public ResponseEntity<?> googleAuth(@RequestBody GoogleAuthRequest request) {
        try {
            // 1. Verify the Google ID token
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(googleClientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(request.getCredential());
            if (idToken == null) {
                return ResponseEntity.status(401).body("Invalid Google token");
            }

            // 2. Extract user info from the verified token
            GoogleIdToken.Payload payload = idToken.getPayload();
            String email = payload.getEmail();
            String name  = (String) payload.get("name");
            if (name == null || name.isBlank()) name = email.split("@")[0];

            // 3. Find or create user
            UserEntity user = userRepository.findByEmail(email).orElse(null);
            if (user == null) {
                // First time Google login → auto-register
                user = new UserEntity();
                user.setEmail(email);
                user.setName(name);
                user.setPassword(null);   // no password for Google users
                user.setProvider("GOOGLE");
                userRepository.save(user);
            }

            // 4. Issue our own JWT and return it
            String token = jwtUtil.generateToken(user.getEmail());
            return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Google authentication failed: " + e.getMessage());
        }
    }
}
