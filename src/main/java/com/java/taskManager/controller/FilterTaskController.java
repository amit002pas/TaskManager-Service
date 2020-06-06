package com.java.taskManager.controller;

import com.java.taskManager.request.FilterTaskRequest;
import com.java.taskManager.response.TaskResponse;
import com.java.taskManager.service.FilterTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class FilterTaskController {


    @Autowired
    FilterTaskService filterTaskService;

    @RequestMapping(path = "/filter-tasks",method = RequestMethod.POST)
    public ResponseEntity<TaskResponse> getFilteredTask(@RequestBody FilterTaskRequest filterTaskRequest, @RequestParam int offset
            ,@RequestParam int size ) throws Exception{

        TaskResponse filteredTask = filterTaskService.getFilteredTask(filterTaskRequest, offset, size);
        return new ResponseEntity(filteredTask, HttpStatus.OK);

    }
}
