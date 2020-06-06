package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.model.Task;
import com.java.taskManager.repository.TaskDao;
import com.java.taskManager.response.TaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpilledTaskService {


    @Autowired
    TaskDao taskDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( SpilledTaskService.class );


    public TaskResponse getAllSpilledTask(String emailId,int offset,int size) throws Exception{

        TaskResponse taskResponse = new TaskResponse();
        try {
            List<Task> taskList = taskDao.getSpilledTasks(emailId,offset,size);
            LOGGER.info("Fetched all completed task for user - ",emailId);
            taskResponse.setStatus(Constants.SUCCESS);
            taskResponse.setMessage("Getting all completed task for "+emailId);
            taskResponse.setTaskList(taskList);
            return taskResponse;
        }
        catch (Exception e) {
            LOGGER.error("Exception:"+e);
            throw new Exception(e.getMessage());
        }

    }
}
