package com.java.taskManager.repository;

import com.mongodb.client.result.UpdateResult;

public interface UserDetailDao {


    public <T> UpdateResult updateField(String queryFieldName,String queryFieldValue,String updateFieldName,T updateFieldValue) throws Exception;

}


