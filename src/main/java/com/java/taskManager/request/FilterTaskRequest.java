package com.java.taskManager.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterTaskRequest {

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("task_label")
    private String taskLabels;

    @JsonProperty("task_priority")
    private String taskPriority;

    @JsonProperty("task_status")
    private String taskStatus;

    @JsonProperty("task_start_date")
    private String taskStartDate;

    @JsonProperty("task_end_date")
    private String taskEndDate;

}
