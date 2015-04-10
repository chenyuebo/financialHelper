package com.yuebo.financialhelper.utils;

import com.yuebo.financialhelper.StatisticType;

import java.util.Calendar;
import java.util.Date;

import static com.yuebo.financialhelper.StatisticType.*;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public class DateUtil {

    public static Date[] getStartDateAndEndDate(StatisticType type, int num) {
        Date[] datas = new Date[2];
        Calendar calendar = Calendar.getInstance();
        switch (type) {
            case DAY:
                calendar.set(Calendar.DAY_OF_YEAR,num);
                datas[0] = calendar.getTime();
                System.out.println(datas[0].toString());
                break;
            case WEEK:
                calendar.set(Calendar.WEEK_OF_YEAR,num);
                datas[0] = calendar.getTime();
                System.out.println(datas[0].toString());
                break;
            case MONTH:
                break;
            case YEAR:
                break;
        }
        return datas;
    }
}
