package com.java.taskManager.repository;

import com.java.taskManager.model.Task;
import com.java.taskManager.request.FilterTaskRequest;

import java.util.Date;
import java.util.List;

public interface TaskDao {

    void addNewTask(Task task) throws Exception;

    void  updateTask(Task task) throws Exception;

    List<Task> getAllTasks(String emailId,int offset,int size) throws Exception;

    List<Task> getSpilledTasks(String emailId,int offset,int size) throws Exception;

    List<Task> getTaskBetweenTimeGivenDueDate(String userID , Date startDueDate , Date endDueDate) throws Exception;

    List<Task> getFilteredTask(FilterTaskRequest filterTaskRequest , int offset,int size) throws Exception;

    Task deleteGivenTask(long taskId) throws Exception;
}
