package com.java.taskManager.service;

import com.java.taskManager.repository.UserDetailDao;
import com.java.taskManager.request.ResetPasswordRequest;
import com.java.taskManager.response.GenericResponse;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

    @Autowired
    UserDetailDao userDetailDao;

    public GenericResponse resetPasswordLogic(ResetPasswordRequest resetPasswordRequest) throws Exception{

        GenericResponse genericResponse = new GenericResponse();

        UpdateResult updateResult= userDetailDao.updateField("email_id",resetPasswordRequest.getUserId(),"password",
                BCrypt.hashpw(resetPasswordRequest.getNewPassword(),BCrypt.gensalt()));
        if(updateResult.getMatchedCount()==1){
            genericResponse.setMessage("Password Reset Successfully");
            genericResponse.setStatus("Success");
        }
        else{
            throw new Exception("Error in Resetting Password");
        }
        return genericResponse;
    }
}
