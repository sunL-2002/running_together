package com.yu.service.impl;

import com.yu.service.TokenValidator;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenValidatorImpl implements TokenValidator {

    @Override
    public boolean isTokenExpired(String token) {
        if (token == null || token.isEmpty()) {
            return true;
        }

        // 假设token中包含了过期时间戳，例如："token:1629457864"
        try {
            String[] parts = token.split(":");
            if (parts.length != 2) {
                return true;
            }

            String timestampStr = parts[1];
            long timestamp = Long.parseLong(timestampStr);
            LocalDateTime expirationTime = LocalDateTime.ofInstant(Date.from(Instant.ofEpochMilli(timestamp)).toInstant(), ZoneId.systemDefault());
            LocalDateTime now = LocalDateTime.now();

            return now.isAfter(expirationTime);
        } catch (Exception e) {
            return true;
        }
    }
}
