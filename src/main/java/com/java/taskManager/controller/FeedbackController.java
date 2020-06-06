package com.java.taskManager.controller;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.request.FeedbackRequest;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task/feedback")
public class FeedbackController {


    @Autowired
    FeedbackService feedbackService;

    @PostMapping
    ResponseEntity<GenericResponse> postFeedback(@RequestBody FeedbackRequest feedbackRequest) throws Exception{

        GenericResponse genericResponse=feedbackService.postFeedbackData(feedbackRequest);
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);

    }
}
