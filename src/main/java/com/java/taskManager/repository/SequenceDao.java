package com.java.taskManager.repository;

import com.java.taskManager.exceptions.MongoDBException;

public interface SequenceDao {

    long getNextSequenceId(String key) throws MongoDBException;

}