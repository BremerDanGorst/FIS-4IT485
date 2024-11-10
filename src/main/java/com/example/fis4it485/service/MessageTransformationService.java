package com.example.fis4it485.service;

import com.example.fis4it485.model.EnvelopeWrapperResponse;
import lombok.RequiredArgsConstructor;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class MessageTransformationService {
    private static final Logger logger = Logger.getLogger(MessageTransformationService.class.getName());

    private final ProducerTemplate producerTemplate;

    public EnvelopeWrapperResponse sendMessage(Map<String, Object> messageBody) {
        logger.info("Provided input: " + messageBody);
        try {
            return producerTemplate.requestBody("direct:start", messageBody.getOrDefault("content",
                    "Default text to show functionality"), EnvelopeWrapperResponse.class);
        } catch (Exception e) {
            logger.info("Further investigation required: \n" + e);
            return EnvelopeWrapperResponse.builder().build();
        }
    }
}
