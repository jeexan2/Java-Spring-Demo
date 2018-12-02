package com.sit.jbc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeUtil {

    public static String getCurrentTimeStampInGmt(){

        ZoneId zoneId = ZoneId.systemDefault();
        OffsetDateTime odt = OffsetDateTime.now ();
        ZoneOffset zoneOffset = odt.getOffset ();
        String gmt = "GMT"+ zoneOffset.toString() + " "+zoneId;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss a");
        String localDateTime = now.format(formatter);
        String timestamp = localDateTime +" "+gmt;
        return timestamp;
    }

    public static String getCurrentTimeStamp(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(" dd-MM-yyyy hh:mm:ss a");
        String timestamp = sdf.format(date);
        return timestamp;
    }


    public static String getCurrentTimeStampInGmtTwo(){

        ZoneId zoneId = ZoneId.systemDefault();
        OffsetDateTime odt = OffsetDateTime.now ();
        ZoneOffset zoneOffset = odt.getOffset ();
        String gmt = "GMT"+ zoneOffset.toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        String localDateTime = now.format(formatter);
        String timestamp = localDateTime +" "+gmt;
        return timestamp;
    }


    public static String convertDateToString(String time){
        String inputPattern = "yyyy-MM-dd HH:mm:ss a";

        String outputPattern = "dd-MMM-yy";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static String getTodayDate(){
        String str = "";
        String pattern = "yyyy-MM-dd";
        Date date = new Date();
        SimpleDateFormat outputFormat = new SimpleDateFormat(pattern);
        str = outputFormat.format(date);
        return str;
    }
}
