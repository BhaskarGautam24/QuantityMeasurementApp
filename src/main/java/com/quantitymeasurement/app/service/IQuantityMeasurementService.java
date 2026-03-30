package com.quantitymeasurement.app.service;

import com.quantitymeasurement.app.dto.QuantityInputDTO;
import com.quantitymeasurement.app.dto.ResponseDTO;
import java.util.List;

public interface IQuantityMeasurementService {
    ResponseDTO convertQuantities(QuantityInputDTO dto, String userEmail);
    List<ResponseDTO> getHistoryForUser(String userEmail);
}
