package com.java.taskManager.controller;

import com.java.taskManager.exceptions.InvalidRequest;
import com.java.taskManager.request.ResetPasswordRequest;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.ResetPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/task/reset")
public class ResetPasswordController {

    @Autowired
    ResetPasswordService resetPasswordService;


    @PostMapping
    public ResponseEntity<GenericResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest resetPasswordRequest, BindingResult bindingResult) throws Exception{

        if(bindingResult.hasErrors()){
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            String field = bindingResult.getAllErrors().get(0).getCodes()[0];
            throw new InvalidRequest(field + " " + message);

        }
        GenericResponse genericResponse = resetPasswordService.resetPasswordLogic(resetPasswordRequest);

        return new ResponseEntity<>(genericResponse, HttpStatus.CREATED);

    }



}
