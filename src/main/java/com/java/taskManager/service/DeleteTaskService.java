package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.model.Task;
import com.java.taskManager.repository.TaskDao;
import com.java.taskManager.response.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteTaskService {

    @Autowired
    private TaskDao taskDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( DeleteTaskService.class );



    public GenericResponse deleteTask(long taskId) throws Exception{

        GenericResponse genericResponse = new GenericResponse();
        Task deletedTask = taskDao.deleteGivenTask(taskId);
        if(deletedTask !=null){
            LOGGER.info("Task deleted successfully:"+deletedTask.getTaskId());
            genericResponse.setMessage("Task deleted successfully");
            genericResponse.setStatus(Constants.SUCCESS);
        }
        else{
            throw new Exception("Some error occured while delting");
        }

        return genericResponse;

    }
}
