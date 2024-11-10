package com.example.fis4it485.service;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class EnvelopeWrapperService extends RouteBuilder {
    private static final Logger logger = Logger.getLogger(EnvelopeWrapperService.class.getName());


    public String transformMessage(String input) {
        logger.info("Transforming message: " + input);
        return input.toUpperCase();
    }

    @Override
    public void configure() throws Exception {
        from("direct:start")
                .process(exchange -> {
                    var originalMessage = exchange.getIn();
                    var envelopeMessage = new DefaultMessage(exchange.getContext());
                    envelopeMessage.setBody(originalMessage.getBody());
                    envelopeMessage.setHeader("MyHeader", "HeaderValue");
                    // Add more headers or metadata as needed
                    exchange.getIn().setBody(envelopeMessage);
                })
                .to("direct:end");
    }
}
