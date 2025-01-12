package com.mytransformation.cetes.controllers;

import java.time.LocalDateTime;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.mytransformation.cetes.config.App;
import com.mytransformation.cetes.models.HealthCheck;

@RestController
@RequestMapping(App.API_PATH + "/v1/health/check")
public class HealthController {

    @GetMapping
    public ResponseEntity<HealthCheck> check() {
        HealthCheck healthCheck = new HealthCheck();
        healthCheck.setService(App.SERVICE_NAME);
        healthCheck.setDate(LocalDateTime.now());

        return new ResponseEntity<HealthCheck>(healthCheck, HttpStatus.OK);
    }
    
}
