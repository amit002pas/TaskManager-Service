package com.java.taskManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.java.taskManager.enums.TaskLabels;
import com.java.taskManager.enums.TaskPriority;
import com.java.taskManager.enums.TaskStatus;
import lombok.*;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@JsonPropertyOrder({
        "_id",
        "email_id",
        "task_name",
        "task_label",
        "task_status",
        "task_priority",
        "task_due_date",
        "task_created_timestamp",
        "task_updated_timestamp"
})


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "task_details")
public class Task {

    @JsonProperty("_id")
    @Field("_id")
    private long taskId;

    @JsonProperty("email_id")
    @Field("email_id")
    private String emailId;

    @JsonProperty(value = "task_name")
    @Field("task_name")
    private String taskName;

    @JsonProperty("task_label")
    @Field("task_label")
    private TaskLabels TaskLabel;

    @JsonProperty("task_status")
    @Field("task_status")
    private TaskStatus taskStatus;

    @JsonProperty("task_priority")
    @Field("task_priority")
    private TaskPriority taskPriority;

    @JsonProperty("task_due_date")
    @Field("task_due_date")
    private Date taskDueDate;

    @JsonProperty("task_created_timestamp")
    @Field("task_created_timestamp")
    private Date taskCreatedTimestamp;

    @JsonProperty("task_updated_timestamp")
    @Field("task_updated_timestamp")
    private Date taskUpdatedTimestamp;
}
