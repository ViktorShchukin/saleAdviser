package ru.aquamarina.saleadviser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {

    @GetMapping("/test")
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Ok");
    }
}
