package com.java.taskManager.repository;

import com.java.taskManager.model.Feedback;

public interface FeedbackDao {

    void addFeedback(Feedback feedback) throws Exception;

}
