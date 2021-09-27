package com.example.pack.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class test {
    public static void main(String[] args) throws ParseException {


        String s = "10:30 PM";

        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");

        Date date = parseFormat.parse(s);

        System.out.println(parseFormat.format(date));
        System.out.println(displayFormat.format(date));




        try {
            String _12HourTime = "2:15 AM";
            SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");

//            Date _24HourDt = _24HourSDF.parse(_24HourTime);
            Date _12HourDt = _12HourSDF.parse(_12HourTime);

            System.out.println(_12HourDt);

//            System.out.println(_12HourSDF.format(_12HourDt));
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("-----Current time of your time zone-----");
        LocalTime time = LocalTime.now();
        System.out.println("Current time of the day: " + time);


        System.out.println("============================");

        System.out.println("-----Current time of a different time zone using LocalTime-----");
        ZoneId zoneId = ZoneId.of("Asia/Dhaka");
        LocalTime localTime=LocalTime.now(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss");
        String formattedTime=localTime.format(formatter);
        System.out.println(localTime);





        System.out.println("============================");

        getCurrentTimeUsingDate();

        System.out.println("============================");

        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String dateBeforeString = "31 01 2014";
        String dateAfterString = "02 02 2014";

        try {
            Date dateBefore = myFormat.parse(dateBeforeString);
            Date dateAfter = myFormat.parse(dateAfterString);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            /* You can also convert the milliseconds to days using this method
             * float daysBetween =
             *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
             */
            System.out.println("Number of Days between dates: "+daysBetween);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("============================");
    }


    public static void getCurrentTimeUsingDate() {
        Date date = new Date();
        String strDateFormat = "hh:mm:ss a";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        System.out.println("Current time of the day using Date - 12 hour format: " + formattedDate);
    }
}

// https://www.youtube.com/watch?v=jltnwMjQoEE
// https://www.youtube.com/watch?v=CqGG-agNu-M
// https://www.youtube.com/watch?v=a4FELDK19Ak
