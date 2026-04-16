package com.quantitymeasurement.measurement.client;

import com.quantitymeasurement.measurement.dto.HistoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign client to communicate with user-service.
 * 'name' must match the spring.application.name of user-service exactly.
 * Spring generates a full HTTP client at runtime — no implementation needed.
 */
@FeignClient(name = "user-service", fallback = UserServiceClientFallback.class)
public interface UserServiceClient {

    /**
     * Save a conversion/arithmetic history record.
     * Maps to POST /api/internal/history in user-service.
     */
    @PostMapping("/api/internal/history")
    HistoryDTO saveHistory(@RequestBody HistoryDTO dto);

    /**
     * Get all history records for a user.
     * Maps to GET /api/internal/history?email=... in user-service.
     */
    @GetMapping("/api/internal/history")
    List<HistoryDTO> getHistory(@RequestParam("email") String email);
}
