package com.java.taskManager.controller;



import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordController {

    @Autowired
    ForgotPasswordService forgotPasswordService;

    @RequestMapping(path = "/task/forgot",method = RequestMethod.GET)
    public ResponseEntity<GenericResponse> forgotPassword(@RequestParam String userId){

        GenericResponse forgotPasswordResponse=new GenericResponse();

        forgotPasswordService.forgetPasswordLogic(userId,forgotPasswordResponse);

        return new ResponseEntity<>(forgotPasswordResponse, HttpStatus.CREATED);



    }
}
