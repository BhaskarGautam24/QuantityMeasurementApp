package com.quantitymeasurement.user.controller;

import com.quantitymeasurement.user.dto.HistoryDTO;
import com.quantitymeasurement.user.entity.QuantityMeasurementEntity;
import com.quantitymeasurement.user.repository.IQuantityMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Internal API for inter-service communication.
 * Called by measurement-service via Feign to save/retrieve conversion history.
 */
@RestController
@RequestMapping("/api/internal")
public class HistoryController {

    @Autowired
    private IQuantityMeasurementRepository repository;

    /**
     * Save a conversion/arithmetic history record.
     * Called by measurement-service after performing a conversion.
     */
    @PostMapping("/history")
    @ResponseStatus(HttpStatus.CREATED)
    public HistoryDTO saveHistory(@RequestBody HistoryDTO dto) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setOperation(dto.getOperation());
        entity.setOperand1(dto.getOperand1());
        entity.setOperand2(dto.getOperand2());
        entity.setResult(dto.getResult());
        entity.setUserEmail(dto.getUserEmail());
        repository.save(entity);

        // Return the saved data back
        dto.setResultString(entity.getOperation() + " | " + entity.getOperand1()
                + " , " + entity.getOperand2() + " → " + entity.getResult());
        return dto;
    }

    /**
     * Get all history records for a user.
     * Called by measurement-service to fetch history.
     */
    @GetMapping("/history")
    public List<HistoryDTO> getHistory(@RequestParam String email) {
        return repository.findByUserEmail(email)
                .stream()
                .map(e -> {
                    HistoryDTO dto = new HistoryDTO();
                    dto.setOperation(e.getOperation());
                    dto.setOperand1(e.getOperand1());
                    dto.setOperand2(e.getOperand2());
                    dto.setResult(e.getResult());
                    dto.setUserEmail(e.getUserEmail());
                    dto.setResultString(e.getOperation() + " | " + e.getOperand1()
                            + " , " + e.getOperand2() + " → " + e.getResult());
                    dto.setResultValue(parseDouble(e.getResult()));
                    dto.setUnit(e.getOperand2());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    private double parseDouble(String val) {
        try { return Double.parseDouble(val.trim().split(" ")[0]); }
        catch (Exception e) { return 0.0; }
    }
}
