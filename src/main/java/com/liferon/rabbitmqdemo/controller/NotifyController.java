package com.liferon.rabbitmqdemo.controller;

import com.liferon.rabbitmqdemo.component.QueueProducer;
import com.liferon.rabbitmqdemo.dto.NotificationRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/notify")
public class NotifyController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private QueueProducer producer;

    @PostMapping
    public ResponseEntity<?> sendNotification(HttpServletRequest request, @RequestBody NotificationRequestDTO notificationDto) {

        notificationDto.setIpAddress(request.getRemoteAddr());
        try {
            producer.produce(notificationDto);
        } catch (Exception e) {
            logger.error("Exception: "+e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.accepted().build();
    }
}
