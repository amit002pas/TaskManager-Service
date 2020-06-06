package com.java.taskManager.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.taskManager.enums.TaskLabels;
import com.java.taskManager.enums.TaskPriority;
import com.java.taskManager.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
@ToString
public class AddNewTaskRequest {

    @NotNull(message = "Task Name can't be empty")
    @JsonProperty("task_name")
    private String taskName;

    @NotNull(message = "Failed to return user id")
    @JsonProperty("email_id")
    private String emailId;

    @NotNull(message = "Task Status can't be empty")
    @JsonProperty("task_status")
    private TaskStatus taskStatus;

    @NotNull(message = "Task label cannot be empty")
    @JsonProperty("task_label")
    private TaskLabels taskLabel;

    @NotNull(message = "Task Priority cannot be empty")
    @JsonProperty("task_priority")
    private TaskPriority taskPriority;

    @NotNull(message = "Task due date cannot be null")
    @JsonProperty("task_due_date")
    private String taskDueDate;
}
