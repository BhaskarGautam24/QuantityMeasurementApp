package com.quantitymeasurement.user.repository;

import com.quantitymeasurement.user.entity.QuantityMeasurementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IQuantityMeasurementRepository extends JpaRepository<QuantityMeasurementEntity, Long> {
    List<QuantityMeasurementEntity> findByUserEmail(String userEmail);
}
