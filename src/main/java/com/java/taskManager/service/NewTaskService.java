package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.Task;
import com.java.taskManager.repository.TaskDao;
import com.java.taskManager.request.AddNewTaskRequest;
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
public class NewTaskService {

    @Autowired
    private TaskDao taskDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( NewTaskService.class );


    public GenericResponse addTask(AddNewTaskRequest addNewTaskRequest) throws Exception {
        GenericResponse genericResponse = new GenericResponse();
        Task task = new Task();

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_PATTERN);
            Date dueDate=sdf.parse(addNewTaskRequest.getTaskDueDate());
            BeanUtils.copyProperties(addNewTaskRequest, task);
            task.setTaskDueDate(dueDate);
            taskDao.addNewTask(task);
            LOGGER.info("Task added successfully for user - " + addNewTaskRequest.getEmailId());
            genericResponse.setStatus(Constants.SUCCESS);
            genericResponse.setMessage("Task added successfully");
        }
        catch (ParseException e){
            LOGGER.error("Parse Exception:"+e);
            throw new ParseException(e.getMessage(),e.getErrorOffset());
        }
        catch (MongoDBException e){
            LOGGER.error("Mongo Exception:"+e);
            throw new MongoDBException(e.getMessage());
        }
        catch (Exception e){
            LOGGER.error("Exception:"+e);
            throw new Exception(e.getMessage());
        }

        return genericResponse;
    }
}
