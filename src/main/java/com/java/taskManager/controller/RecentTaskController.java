package com.java.taskManager.controller;

import com.java.taskManager.response.RecentTaskResponse;
import com.java.taskManager.service.RecentTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class RecentTaskController {

    @Autowired
    private RecentTaskService recentTaskService;

    @RequestMapping(path = "/recent-tasks",method = RequestMethod.GET)
    public ResponseEntity<RecentTaskResponse> getRecentTask(@RequestParam (value = "email_id") String userId) throws Exception{
        RecentTaskResponse recentTaskResponse =  recentTaskService.getAllRecentTask(userId);
        return new ResponseEntity<>(recentTaskResponse, HttpStatus.OK);

    }

}
