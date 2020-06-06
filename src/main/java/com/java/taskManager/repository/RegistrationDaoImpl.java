package com.java.taskManager.repository;

import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

    @Autowired
    private MongoOperations mongoOperations;

    private static final Logger LOGGER = LoggerFactory.getLogger( RegistrationDaoImpl.class );


    @Override
    public void registerUser(UserDetails userDetails) throws Exception {
        try {
                mongoOperations.save(userDetails);
        } catch (DuplicateKeyException e){
            throw new MongoDBException("Email id is already registered");
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
