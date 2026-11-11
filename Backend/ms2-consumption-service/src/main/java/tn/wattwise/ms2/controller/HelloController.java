package tn.wattwise.ms2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ms2")
@Tag(name = "MS2 Consumption Service", description = "Basic test endpoints")
public class HelloController {

    @Operation(summary = "Test endpoint")
    @GetMapping("/hello")
    public String hello() {
        return "Hello from MS2 - Consumption Service!";
    }
}