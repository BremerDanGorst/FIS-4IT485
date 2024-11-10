package com.example.fis4it485.controller;

import com.example.fis4it485.model.EnvelopeWrapperResponse;
import com.example.fis4it485.service.MessageTransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
public class MessageTransformationController {
    private final MessageTransformationService messageTransformationService;

    @PostMapping(value = "/envelopeWrapper", produces = "application/json")
    public ResponseEntity<EnvelopeWrapperResponse> envelopeWrapperExample(@RequestBody Map<String, Object> messageBody) {
        final var result = messageTransformationService.sendMessage(messageBody);
        return result.isSuccessfullyUpdated() ? ResponseEntity.ok(result) : ResponseEntity.status(INTERNAL_SERVER_ERROR).body(result);
    }
}
