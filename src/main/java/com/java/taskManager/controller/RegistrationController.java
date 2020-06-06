package com.java.taskManager.controller;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.exceptions.InvalidRequest;
import com.java.taskManager.request.RegistrationRequest;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.RegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/task/register")
public class RegistrationController {

    private static final Logger LOGGER = LoggerFactory.getLogger( RegistrationController.class );

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity registerNewUser(@Valid @RequestBody RegistrationRequest registrationRequest, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            String field = bindingResult.getAllErrors().get(0).getCodes()[0];

            LOGGER.error("Invalid Task Request - "+field+" "+message);
            throw new InvalidRequest(message);
        }
        GenericResponse genericResponse = registrationService.newUser(registrationRequest);
        if(Constants.SUCCESS.equalsIgnoreCase(genericResponse.getStatus())){
            return new ResponseEntity(genericResponse, HttpStatus.CREATED);
        }
        else return new ResponseEntity<>(genericResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
