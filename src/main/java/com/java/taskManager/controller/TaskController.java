package com.java.taskManager.controller;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.response.TaskResponse;
import com.java.taskManager.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    private static final Logger LOGGER = LoggerFactory.getLogger( TaskController.class );

    @GetMapping
    public ResponseEntity<TaskResponse> getAllTasks(@RequestParam (value = "email_id") String emailId,@RequestParam int offset,@RequestParam int size) throws Exception {
        LOGGER.info("Getting all tasks for user - ", emailId);
       TaskResponse taskResponse = taskService.getAllTaskDocuments(emailId,offset,size);
       if(Constants.SUCCESS.equalsIgnoreCase(taskResponse.getStatus())){
           return new ResponseEntity<>(taskResponse, HttpStatus.OK);
       }
       else return new ResponseEntity<>(taskResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
