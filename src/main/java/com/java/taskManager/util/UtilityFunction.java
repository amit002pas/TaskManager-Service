package com.java.taskManager.util;

import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class UtilityFunction {

    public static Date getFormattedDate(Calendar startDate, int hour, int min, int sec) {
        Date formattedStartDate;
        startDate.set(Calendar.HOUR_OF_DAY, hour);
        startDate.set(Calendar.MINUTE, min);
        startDate.set(Calendar.SECOND, sec);
        formattedStartDate = new Date(startDate.getTimeInMillis());
        return formattedStartDate;
    }
}
