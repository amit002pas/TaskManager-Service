package com.java.taskManager.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.taskManager.enums.TaskLabels;
import com.java.taskManager.enums.TaskPriority;
import com.java.taskManager.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UpdateTaskRequest {

    @JsonProperty("task_id")
    private long taskId;

    @JsonProperty("task_name")
    private String taskName;

    @JsonProperty("email_id")
    private String emailId;

    @JsonProperty("task_status")
    private TaskStatus taskStatus;

    @JsonProperty("task_label")
    private TaskLabels taskLabel;

    @JsonProperty("task_priority")
    private TaskPriority taskPriority;

    @JsonProperty("task_due_date")
    private String taskDueDate;
}
