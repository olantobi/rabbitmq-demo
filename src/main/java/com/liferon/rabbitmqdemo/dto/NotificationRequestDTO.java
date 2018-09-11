package com.liferon.rabbitmqdemo.dto;

import lombok.Data;

@Data
public class NotificationRequestDTO {

    private String name;
    private String email;
    private String message;
    private String ipAddress;

}
