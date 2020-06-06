package com.java.taskManager.controller;

import com.java.taskManager.request.UpdateTaskRequest;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.UpdateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class UpdateTaskController {

    @Autowired
    private UpdateTaskService updateTaskService;

    @RequestMapping(path = "/update",method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> updateTask(@RequestBody UpdateTaskRequest updateTaskRequest) throws Exception{

        GenericResponse genericResponse = updateTaskService.updateGivenTask(updateTaskRequest);

        return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);

    }
}
