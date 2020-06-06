package com.java.taskManager.service;

import com.java.taskManager.repository.UserDetailDao;
import com.java.taskManager.repository.UserDetailDaoImpl;
import com.java.taskManager.response.GenericResponse;
import com.java.taskManager.util.CommonUtil;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ForgotPasswordService {

    @Autowired
    UserDetailDao userDetailDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( UserDetailDaoImpl.class );


    public void forgetPasswordLogic(String userId, GenericResponse forgotPasswordResponse){

        try {
            String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
            String specialCharacters = "!@#$";
            String numbers = "1234567890";
            String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
            Random random = new Random();
            char[] password = new char[8];

            for (int i = 0; i < 8; i++) {
                password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
            }
            UpdateResult updateResult = userDetailDao.updateField("email_id",userId,"token",String.valueOf(password));

            if (updateResult.getMatchedCount() == 1) {

                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setTo(userId);
                String SUBJECT="Forgot Password";
                String BODY="This is an auto response mail from Task Manager Web site in response to your query on Lost Password \n" +
                        "Password:" + String.valueOf(password) + "\n" + "\n" + "Regards," + "\n" + "Task Manager Team!!!";


                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(()-> {
                    try {
                        CommonUtil.sendMail(userId,SUBJECT,BODY);
                    }
                    catch (Exception e){
                        System.out.println("Error in Sending mail(Forgot):"+e);
                    }
                });
                forgotPasswordResponse.setMessage("Temporary password sent to registered email id");
                forgotPasswordResponse.setStatus("Success");
            } else {
                forgotPasswordResponse.setStatus("Failure");
                forgotPasswordResponse.setMessage("User does not exist");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

        System.out.println("Bye Bye");

    }
}
