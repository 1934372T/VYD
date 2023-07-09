package com.nat.nat.lib.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeOperator {

    public String extractTermFromDate(LocalDateTime date) {
        int year = date.getYear();
        int month = date.getMonthValue();

        if(month < 4) {
            year--;
        }

        return Integer.toString(year);
    }

    public String japaneseDateConverter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        return date.format(formatter);
    }
}
