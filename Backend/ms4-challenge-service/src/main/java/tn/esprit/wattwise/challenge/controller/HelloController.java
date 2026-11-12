package tn.esprit.wattwise.challenge.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "MS4 Challenge Service", description = "Session 4 check endpoints")
public class HelloController {

    @Operation(summary = "Check that MS4 is running")
    @GetMapping("/hello")
    public Map<String, String> hello() {
        return Map.of(
                "service", "ms4-challenge-service",
                "message", "Hello from MS4 - Energy Challenges & Rewards!"
        );
    }

    @Operation(summary = "Send a message to MS4 and get it back (POST check)")
    @PostMapping("/hello")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> helloPost(@RequestBody(required = false) Map<String, String> body) {
        String message = (body == null) ? null : body.get("message");
        return Map.of(
                "service", "ms4-challenge-service",
                "message", message == null || message.isBlank() ? "hello" : message
        );
    }
}
