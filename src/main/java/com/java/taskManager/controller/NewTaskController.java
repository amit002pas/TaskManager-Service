package com.java.taskManager.controller;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.exceptions.InvalidRequest;
import com.java.taskManager.request.AddNewTaskRequest;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.NewTaskService;
import com.java.taskManager.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
public class NewTaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger( TaskController.class );

    @Autowired
    private NewTaskService newTaskService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public ResponseEntity<GenericResponse> addNewTask(@Valid @RequestBody AddNewTaskRequest addNewTaskRequest, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            String field = bindingResult.getAllErrors().get(0).getCodes()[0];

            LOGGER.error("Invalid Task Request - "+field+" "+message);
            throw new InvalidRequest(message);
        }
        GenericResponse genericResponse = newTaskService.addTask(addNewTaskRequest);
        if(Constants.SUCCESS.equalsIgnoreCase(genericResponse.getStatus())) {
            return  new ResponseEntity<>(genericResponse, HttpStatus.OK);
        }
        else return new ResponseEntity<>(genericResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
