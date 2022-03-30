package com.server.praktika.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/date")
public class DateController {
    @GetMapping("")
    public ResponseEntity<?> getServerDate() {
        return ResponseEntity.ok(new Date());
    }
}
