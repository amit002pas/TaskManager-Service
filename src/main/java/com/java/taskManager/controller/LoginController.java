package com.java.taskManager.controller;

import com.java.taskManager.response.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger( LoginController.class );

    @GetMapping("/login")
    public LoginResponse login() {
        LOGGER.info("authenticating");
        return new LoginResponse("You are authenticated");
    }
}
