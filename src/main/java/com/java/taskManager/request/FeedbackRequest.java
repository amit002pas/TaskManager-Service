package com.java.taskManager.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FeedbackRequest {

    @JsonProperty("rating")
    private int rating;
    @JsonProperty("feedback_message")
    private String feedbackMessage;

}