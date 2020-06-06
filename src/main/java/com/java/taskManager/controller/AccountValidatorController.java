package com.java.taskManager.controller;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.AccountValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountValidatorController {

    @Autowired
    AccountValidatorService accountValidatorService;

    @RequestMapping(path="/validate/{uuid}",produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> validateAccount(@PathVariable String uuid) throws Exception{


        GenericResponse genericResponse = accountValidatorService.accountValidator(uuid);
        if(Constants.SUCCESS.equalsIgnoreCase(genericResponse.getStatus()))
            return new ResponseEntity(genericResponse.getMessage(), HttpStatus.OK);
        else
            return new ResponseEntity("Activation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
