package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.model.Task;
import com.java.taskManager.repository.TaskDao;
import com.java.taskManager.repository.TaskDaoImpl;
import com.java.taskManager.request.UpdateTaskRequest;
import com.java.taskManager.response.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UpdateTaskService {

    @Autowired
    private TaskDao taskDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( UpdateTaskService.class );


    public GenericResponse updateGivenTask(UpdateTaskRequest updateTaskRequest) throws Exception{

        GenericResponse genericResponse = new GenericResponse();
        Task task = new Task();
        try {

            BeanUtils.copyProperties(updateTaskRequest, task);
            if(updateTaskRequest.getTaskDueDate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN);
                Date dueDate = sdf.parse(updateTaskRequest.getTaskDueDate());
                task.setTaskDueDate(dueDate);
            }
            taskDao.updateTask(task);
            LOGGER.info("updated task for user - "+updateTaskRequest.getEmailId());
            genericResponse.setStatus(Constants.SUCCESS);
            genericResponse.setMessage("Successfully updated");
        }
        catch(ParseException parseException){
            LOGGER.error("Error in parsing the date:"+parseException);
            throw new ParseException(parseException.getMessage(),parseException.getErrorOffset());
        }
        catch(Exception exception){
            LOGGER.error("Error in task updation"+exception);
            throw new Exception(exception.getMessage());
        }

        return genericResponse;

    }

}
