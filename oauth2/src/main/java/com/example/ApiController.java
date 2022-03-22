package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ApiController {

    @GetMapping(value = "/api/private")
    public Map<String, String> privateApi() {
        Map<String, String> model = new HashMap<>();
        model.put("name", "jihoon");
        model.put("nick", "zef");
        return model;
    }
}
