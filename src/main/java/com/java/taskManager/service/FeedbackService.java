package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.Feedback;
import com.java.taskManager.repository.FeedbackDao;
import com.java.taskManager.request.FeedbackRequest;
import com.java.taskManager.response.GenericResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {


    @Autowired
    FeedbackDao feedbackDao;

    public GenericResponse postFeedbackData(FeedbackRequest feedbackRequest) throws Exception{


        GenericResponse genericResponse=new GenericResponse();
        Feedback feedbackData=new Feedback();
        try{
            BeanUtils.copyProperties(feedbackRequest,feedbackData);
            feedbackDao.addFeedback(feedbackData);
            genericResponse.setMessage("Feedback Inserted Successfully");
            genericResponse.setStatus(Constants.SUCCESS);
        }
        catch (MongoDBException exception){
            throw new MongoDBException(exception.getMessage());
        }
        catch (Exception e){
            throw new Exception("Some Internal Server Error");
        }

        return genericResponse;
    }
}
