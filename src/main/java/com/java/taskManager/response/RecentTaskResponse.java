package com.java.taskManager.response;

import com.java.taskManager.model.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecentTaskResponse {

    private String status;
    private List<Task> todaysTask;
    private List<Task> tomorrowsTask;
}
