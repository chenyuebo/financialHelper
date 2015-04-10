package com.yuebo.financialhelper;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public enum StatisticType {

    DAY(1),WEEK(2),MONTH(3),YEAR(4);

    private int value;

    private StatisticType(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
