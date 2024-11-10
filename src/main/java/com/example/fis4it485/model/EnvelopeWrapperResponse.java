package com.example.fis4it485.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class EnvelopeWrapperResponse {
    private String body;
    private Map<String, Object> headers;
    private boolean isSuccessfullyUpdated;
}
