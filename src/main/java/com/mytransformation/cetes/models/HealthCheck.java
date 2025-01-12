package com.mytransformation.cetes.models;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class HealthCheck {

    String service;

    LocalDateTime date;
    
}
