package com.java.taskManager.repository;

import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.Feedback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class FeedbackDaoImpl implements FeedbackDao{


    @Autowired
    MongoOperations mongoOperations;

    private static final Logger LOGGER = LoggerFactory.getLogger( FeedbackDaoImpl.class );


    @Override
    public void addFeedback(Feedback feedback) throws Exception{
        try{
            mongoOperations.save(feedback);
        }
        catch(Exception exception){
            LOGGER.error("Exception in saving feedback:"+exception);
            throw new MongoDBException("Failed in saving feedback");
        }
    }

}
