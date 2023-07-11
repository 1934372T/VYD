package com.nat.nat.lib.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeOperator {

    private static final String JP_TIME_FORMAT = "yyyy年MM月dd日";

    /*
     * 年月から年度を算出する．
     */
    public String extractTermFromDate(LocalDateTime date) {
        int year = date.getYear();
        int month = date.getMonthValue();

        if(month < 4) {
            year--;
        }

        return Integer.toString(year);
    }

    /*
     * 日本時間表記に変換する．
     */
    public String japaneseDateConverter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(JP_TIME_FORMAT);
        return date.format(formatter);
    }
}
