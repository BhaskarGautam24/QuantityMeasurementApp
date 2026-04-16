package com.quantitymeasurement.measurement.client;

import com.quantitymeasurement.measurement.dto.HistoryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Fallback implementation for UserServiceClient.
 * Used when user-service is unavailable — conversions still work,
 * only history saving is silently skipped (graceful degradation).
 */
@Component
public class UserServiceClientFallback implements UserServiceClient {

    private static final Logger log = LoggerFactory.getLogger(UserServiceClientFallback.class);

    @Override
    public HistoryDTO saveHistory(HistoryDTO dto) {
        log.warn("user-service unavailable — history not saved for user {}", dto.getUserEmail());
        return new HistoryDTO(); // Return empty response, not an error
    }

    @Override
    public List<HistoryDTO> getHistory(String email) {
        log.warn("user-service unavailable — cannot fetch history for user {}", email);
        return Collections.emptyList(); // Return empty list
    }
}
