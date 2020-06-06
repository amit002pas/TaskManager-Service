package com.java.taskManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Setter
@Getter
@Document(collection = "feedback")
public class Feedback {

    @JsonProperty("rating")
    @Field("rating")
    private int rating;

    @JsonProperty("feedback_message")
    @Field("feedback_message")
    private String feedbackMessage;

}