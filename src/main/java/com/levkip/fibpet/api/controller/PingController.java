package com.levkip.fibpet.api.controller;

import com.levkip.fibpet.api.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @GetMapping(value = "rest/api/v1.0/ping")
    public ResponseEntity<Response<String>> ping() {

        return new ResponseEntity<>(new Response<>(true, "pong"), HttpStatus.OK);

    }
}
