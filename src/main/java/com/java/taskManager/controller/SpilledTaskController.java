package com.java.taskManager.controller;

import com.java.taskManager.response.TaskResponse;
import com.java.taskManager.service.SpilledTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class SpilledTaskController {


    @Autowired
    SpilledTaskService spilledTaskService;

    @RequestMapping(path = "/spilled-task", method = RequestMethod.GET)
    public ResponseEntity<TaskResponse> spilledTask(@RequestParam(value = "email_id") String emailId, @RequestParam int offset, @RequestParam int size)
            throws Exception{

           TaskResponse taskResponse =  spilledTaskService.getAllSpilledTask(emailId,offset,size);
           return new ResponseEntity<>(taskResponse, HttpStatus.OK);
    }
}
