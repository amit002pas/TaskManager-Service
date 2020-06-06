package com.java.taskManager.controller;

import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.DeleteTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class DeleteTaskController {

    @Autowired
    private DeleteTaskService deleteTaskService;

    @RequestMapping(path = "/delete/{taskId}" , method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> deleteTask(@PathVariable long taskId) throws Exception{

        GenericResponse genericResponse = deleteTaskService.deleteTask(taskId);

        return new ResponseEntity<>(genericResponse, HttpStatus.ACCEPTED);
    }
}
