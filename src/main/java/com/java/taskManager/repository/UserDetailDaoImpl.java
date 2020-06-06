package com.java.taskManager.repository;

import com.java.taskManager.exceptions.MongoDBException;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserDetailDaoImpl implements  UserDetailDao{

    @Autowired
    MongoOperations mongoOperations;

    private static final Logger LOGGER = LoggerFactory.getLogger( UserDetailDaoImpl.class );


    @Override
    public <T> UpdateResult updateField(String queryFieldName,String queryFieldValue,String updateFieldName,T updateFieldValue) throws Exception{

        try {
            Update update = new Update();
            update.set(updateFieldName, updateFieldValue);
            Query query = new Query(Criteria.where(queryFieldName).is(queryFieldValue));
            return mongoOperations.updateFirst(query, update, "user_details");
        }
        catch (Exception e){
            LOGGER.error("Failed in updating the field"+e);
            throw new MongoDBException("Failed in updating the field");
        }
    }
}
