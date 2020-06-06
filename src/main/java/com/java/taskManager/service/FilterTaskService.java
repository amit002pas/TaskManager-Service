package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.model.Task;
import com.java.taskManager.repository.TaskDao;
import com.java.taskManager.request.FilterTaskRequest;
import com.java.taskManager.response.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FilterTaskService {

    @Autowired
    TaskDao taskDao;

    public TaskResponse getFilteredTask(FilterTaskRequest filterTaskRequest , int offset , int size)throws  Exception{

        TaskResponse taskResponse = new TaskResponse();
        List<Task> task=taskDao.getFilteredTask(filterTaskRequest,offset,size);
        if(task != null){
            taskResponse.setStatus(Constants.SUCCESS);
            taskResponse.setMessage("Successfully fetched the data");
            taskResponse.setTaskList(task);
        }
        else{
            throw new  Exception("Failed in getting data");
        }
        return  taskResponse;

    }
}
