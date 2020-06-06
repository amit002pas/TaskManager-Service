package com.java.taskManager.service;

import com.java.taskManager.constants.Constants;
import com.java.taskManager.model.Task;
import com.java.taskManager.repository.TaskDao;
import com.java.taskManager.response.RecentTaskResponse;
import com.java.taskManager.util.UtilityFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RecentTaskService {

    @Autowired
    private TaskDao taskDao;

    public RecentTaskResponse getAllRecentTask(String userId) throws Exception {

        Date formattedStartDate,formattedEndDate;

        Calendar startDate = Calendar.getInstance();
        formattedStartDate = UtilityFunction.getFormattedDate(startDate,0,0,0);

        Calendar endDate = Calendar.getInstance();
        formattedEndDate = UtilityFunction.getFormattedDate(startDate,23,59,59);

        List<Task> todaysTask =  taskDao.getTaskBetweenTimeGivenDueDate(userId ,formattedStartDate , formattedEndDate);

        startDate = Calendar.getInstance();
        startDate.add(Calendar.DATE,1);
        formattedStartDate = UtilityFunction.getFormattedDate(startDate,0,0,0);

        endDate = Calendar.getInstance();
        endDate.add(Calendar.DATE,1);
        formattedEndDate = UtilityFunction.getFormattedDate(startDate,23,59,59);

        List<Task> tomorrowsTask =  taskDao.getTaskBetweenTimeGivenDueDate(userId ,formattedStartDate , formattedEndDate);

        RecentTaskResponse recentTaskResponse = new RecentTaskResponse();
        recentTaskResponse.setStatus(Constants.SUCCESS);
        recentTaskResponse.setTodaysTask(todaysTask);
        recentTaskResponse.setTomorrowsTask(tomorrowsTask);

        return recentTaskResponse;

    }


}
