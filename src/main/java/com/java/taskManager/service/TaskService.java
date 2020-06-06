package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.Task;
import com.java.taskManager.repository.TaskDao;
import com.java.taskManager.repository.TaskDaoImpl;
import com.java.taskManager.request.AddNewTaskRequest;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.response.TaskResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( TaskService.class );

    public TaskResponse getAllTaskDocuments(String emailId,int offset,int size) throws Exception {

        TaskResponse taskResponse = new TaskResponse();
            List<Task> taskList = taskDao.getAllTasks(emailId,offset,size);
            LOGGER.info("Fetched all the records/documents successfully");
            taskResponse.setMessage("Data fetched successfully");
            taskResponse.setStatus(Constants.SUCCESS);
            taskResponse.setTaskList(taskList);

        return taskResponse;
    }
}
