package com.java.taskManager.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.taskManager.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskResponse {

    @JsonProperty("status")
    private String status;

    @JsonProperty("message")
    private String message;

    @JsonProperty("taskList")
    private List<Task> taskList;
}
