package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.repository.UserDetailDao;
import com.java.taskManager.response.GenericResponse;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountValidatorService {

    @Autowired
    UserDetailDao userDetailDao;

    public GenericResponse accountValidator(String uuid) throws Exception{

        GenericResponse genericResponse= new GenericResponse();
        UpdateResult updateResult=userDetailDao.updateField("token",uuid,"is_verified",true);
        if(updateResult.getMatchedCount()==1L && updateResult.getModifiedCount()==1L )
        {
            genericResponse.setMessage("Account activated Successfully");
            genericResponse.setStatus(Constants.SUCCESS);
        }
        else{
            throw new Exception("failed in activating the account");
        }
        return genericResponse;
    }
}
