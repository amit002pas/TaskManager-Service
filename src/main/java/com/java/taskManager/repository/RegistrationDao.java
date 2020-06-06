package com.java.taskManager.repository;

import com.java.taskManager.model.UserDetails;


public interface RegistrationDao {

    public void registerUser(UserDetails userDetails) throws Exception;
}
