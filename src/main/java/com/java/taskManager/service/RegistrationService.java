package com.java.taskManager.service;


import com.fasterxml.uuid.Generators;
import com.java.taskManager.constants.Constants;
import com.java.taskManager.exceptions.InvalidRequest;
import com.java.taskManager.exceptions.MongoDBException;
import com.java.taskManager.model.UserDetails;
import com.java.taskManager.repository.RegistrationDao;
import com.java.taskManager.repository.RegistrationDaoImpl;
import com.java.taskManager.request.RegistrationRequest;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.util.CommonUtil;
import org.springframework.dao.DuplicateKeyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationDao registrationDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( RegistrationService.class );

    public GenericResponse newUser(RegistrationRequest registrationRequest) throws Exception {
        GenericResponse genericResponse = new GenericResponse();
        UserDetails userDetails = new UserDetails();
        try {
            BeanUtils.copyProperties(registrationRequest, userDetails);
            userDetails.setPassword(BCrypt.hashpw(registrationRequest.getPassword(), BCrypt.gensalt()));
            userDetails.setVerified(false);
            String token= Generators.timeBasedGenerator().generate().toString();
            userDetails.setToken(token);
            registrationDao.registerUser(userDetails);
            String BODY = "Please click on the below link to activate your account\n" +"\n"+
                    "Activation Link: http://ec2-18-217-0-7.us-east-2.compute.amazonaws.com:7072/validate/"+token  + "\n" + "\n" + "Regards," + "\n" + "TaskManager Team!!!";

            String SUBJECT="Activation Link";

            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(()-> {
                try {
                    CommonUtil.sendMail(registrationRequest.getEmailId(),SUBJECT,BODY);
                }
                catch (Exception e){
                    System.out.println("Error in Sending mail(Activation):"+e);
                }
            });
            LOGGER.info("New  user registered successfully " + userDetails.getEmailId());
            genericResponse.setStatus(Constants.SUCCESS);
            genericResponse.setMessage("Activation Link Sent Successfully to " + userDetails.getEmailId());
            return genericResponse;

        }
        catch (Exception e) {
            LOGGER.error("Exception:"+e);
            throw new Exception(e.getMessage());
        }
    }
}
