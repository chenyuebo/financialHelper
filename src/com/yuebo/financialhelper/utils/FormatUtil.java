package com.yuebo.financialhelper.utils;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtil {

//    public static Date stringToDate(String string) {
//        Date date = null;
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern, Locale.CHINA);
//
//        String pattern2 = "yyyy-MM-dd";
//        SimpleDateFormat simpleFormat2 = new SimpleDateFormat(pattern2, Locale.CHINA);
//        try {
//            date = simpleFormat.parse(string);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return date;
//    }
//
//    public static String dateToString(Date date) {
//        String s = null;
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat simpleFormat = new SimpleDateFormat(pattern, Locale.CHINA);
//        s = simpleFormat.format(date);
//        return s;
//    }
//
//    public static String parseDate(String string){
//        try {
//            Date date = DateFormat.getInstance().parse(string);
//            return date.getYear() + "-" + date.getMonth() + "-" + date.getDate();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess = true;
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess = false;
        }
        return convertSuccess;
    }
}
