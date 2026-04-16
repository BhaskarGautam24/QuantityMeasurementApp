package com.quantitymeasurement.measurement.service;

import com.quantitymeasurement.measurement.dto.ArithmeticInputDTO;
import com.quantitymeasurement.measurement.dto.ArithmeticResponseDTO;
import com.quantitymeasurement.measurement.dto.QuantityInputDTO;
import com.quantitymeasurement.measurement.dto.ResponseDTO;
import java.util.List;

public interface IQuantityMeasurementService {
    ResponseDTO convertQuantities(QuantityInputDTO dto, String userEmail);
    List<ResponseDTO> getHistoryForUser(String userEmail);
    ArithmeticResponseDTO performArithmetic(ArithmeticInputDTO dto, String userEmail);
}
