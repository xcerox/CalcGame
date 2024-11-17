package org.doit.logs.infraestructure.in.amqp;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.logging.Level;

@Slf4j
@Service
public class LogEventConsumer {
    @RabbitListener(queues = "${amqp.queue.logs}")
    public void log(final String msg, @Header("level") String level, @Header("amqp_appId") String appId) {
        Marker marker = MarkerFactory.getMarker(appId);

        switch (level) {
            case "INFO" -> log.info(marker, msg);
            case "ERROR" -> log.error(marker, msg);
            case "WARN" -> log.warn(marker, msg);
        }
    }
}
