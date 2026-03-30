package com.quantitymeasurement.app.controller;

import com.quantitymeasurement.app.dto.QuantityInputDTO;
import com.quantitymeasurement.app.dto.ResponseDTO;
import com.quantitymeasurement.app.service.IQuantityMeasurementService;
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
        String userEmail = auth.getName();
        return service.convertQuantities(dto, userEmail);
    }

    @GetMapping("/history")
    public List<ResponseDTO> getHistory(Authentication auth) {
        String userEmail = auth.getName();
        return service.getHistoryForUser(userEmail);
    }
}
