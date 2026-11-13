package tn.esprit.wattwise.solar.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "MS3 Solar Service", description = "Session 4 check endpoint")
public class HelloController {

    @Operation(summary = "Check that MS3 is running")
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "service", "ms3-solar-service",
                "message", "Hello from MS3 - Solar Panel Management!"
        );
    }
}
