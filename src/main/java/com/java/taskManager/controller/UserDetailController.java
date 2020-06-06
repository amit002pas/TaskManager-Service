package com.java.taskManager.controller;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.response.UserDetailResponse;
import com.java.taskManager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class UserDetailController {

    @Autowired
    UserService userService;

    @RequestMapping(path = "/user-details", method = RequestMethod.GET)
    public ResponseEntity getUserDetails(@RequestParam(value = "email_id")String emailId) throws Exception {
        UserDetailResponse userDetailResponse = userService.getUserDetails(emailId);
        if(Constants.SUCCESS.equalsIgnoreCase(userDetailResponse.getStatus())){
                return new ResponseEntity(userDetailResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity(userDetailResponse,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
