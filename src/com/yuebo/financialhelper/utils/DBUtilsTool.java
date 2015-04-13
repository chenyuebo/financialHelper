package com.yuebo.financialhelper.utils;

import android.content.Context;
import com.lidroid.xutils.DbUtils;

/**
 * Created by Administrator on 2015/4/12.
 * Email: 1197421347@qq.com
 */
public class DBUtilsTool {

    public static DbUtils getDBUtils(Context context){
        DbUtils dbUtils = DbUtils.create(
                context
                , "financialHellper.db"
                , 2
                , new DbUtils.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbUtils dbUtils, int oldVersion, int newVersion) {

                    }
                }
        );
        return dbUtils;
    }
}
