package com.quantitymeasurement.measurement.controller;

import com.quantitymeasurement.measurement.dto.ArithmeticInputDTO;
import com.quantitymeasurement.measurement.dto.ArithmeticResponseDTO;
import com.quantitymeasurement.measurement.dto.QuantityInputDTO;
import com.quantitymeasurement.measurement.dto.ResponseDTO;
import com.quantitymeasurement.measurement.service.IQuantityMeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

    @Autowired
    private IQuantityMeasurementService service;

    @PostMapping("/convert")
    public ResponseDTO convert(@RequestBody QuantityInputDTO dto, Authentication auth) {
        String userEmail = (auth != null) ? auth.getName() : null;
        return service.convertQuantities(dto, userEmail);
    }

    @PostMapping("/arithmetic")
    public ArithmeticResponseDTO arithmetic(@RequestBody ArithmeticInputDTO dto, Authentication auth) {
        String userEmail = (auth != null) ? auth.getName() : null;
        return service.performArithmetic(dto, userEmail);
    }

    @GetMapping("/history")
    public List<ResponseDTO> getHistory(Authentication auth) {
        return service.getHistoryForUser(auth.getName());
    }
}
