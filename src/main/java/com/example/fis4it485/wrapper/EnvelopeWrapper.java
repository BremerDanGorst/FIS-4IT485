package com.example.fis4it485.wrapper;

import com.example.fis4it485.model.EnvelopeWrapperResponse;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class EnvelopeWrapper extends RouteBuilder {
    @Override
    public void configure() {
        from("direct:start")
                .process(exchange -> {
                    Message originalMessage = exchange.getIn();
                    Message envelopeMessage = new DefaultMessage(exchange.getContext());
                    envelopeMessage.setBody("This is original message updated by Envelop Wrapper: " +
                            originalMessage.getBody().toString().toUpperCase().replaceAll("\\s+", "_"));
                    envelopeMessage.setHeader("Custom Header", "Required header from the other app");
                    envelopeMessage.setHeader("MessageType", "Advanced");
                    final var now = LocalDateTime.now();
                    final var formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss", Locale.ENGLISH);
                    final var formattedDate = now.format(formatter);
                    envelopeMessage.setHeader("Timestamp", formattedDate);
                    envelopeMessage.setHeader("Priority", now.getDayOfWeek().getValue() <= 5 ? "High" : "Average");

                    var response = EnvelopeWrapperResponse.builder()
                            .body(envelopeMessage.getBody().toString())
                            .headers(envelopeMessage.getHeaders())
                            .isSuccessfullyUpdated(true).build();

                    exchange.getIn().setBody(response);
                })
                .to("direct:end");

        from("direct:end").log("Final message body: ${body} with headers: ${headers}");
    }
}
