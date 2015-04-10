package com.yuebo.financialhelper.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static String DB_NAME = "financialHelper.db";
    private static int VERSION = 1;
    private SQLiteDatabase dbConn;

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
        dbConn = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建码表
        db.execSQL("CREATE TABLE [category_map] ( [id] INTEGER PRIMARY KEY AUTOINCREMENT, [category_name] VARCHAR(50) NOT NULL, [category_code] INTEGER NOT NULL); ");
        // 创建账目表
        db.execSQL("CREATE TABLE [journal_account] ([id] INTEGER PRIMARY KEY AUTOINCREMENT, [money] DECIMAL NOT NULL, [category_code] INTEGER NOT NULL, [date] DATE NOT NULL, [detail] TEXT);");
        // 创建消费视图
        db.execSQL("CREATE VIEW consume_view AS SELECT * FROM journal_account WHERE category_code like '2%';");
        // 收入视图
        db.execSQL("CREATE VIEW make_money_view AS SELECT * FROM journal_account WHERE category_code like '1%';");
        // 吃
        db.execSQL("CREATE VIEW eat_view AS SELECT * FROM journal_account WHERE category_code like '21%';");
        // 穿
        db.execSQL("CREATE VIEW cloth_view AS SELECT * FROM journal_account WHERE category_code like '22%';");
        // 住
        db.execSQL("CREATE VIEW house_view AS SELECT * FROM journal_account WHERE category_code like '23%';");
        // 行
        db.execSQL("CREATE VIEW traffic_view AS SELECT * FROM journal_account WHERE category_code like '24%';");
        // 用
        db.execSQL("CREATE VIEW daily_use_view AS SELECT * FROM journal_account WHERE category_code like '25%';");

        // 初始化码表
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('收入：工资','1101');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('收入：外快','1201');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('吃：日常','2101');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('吃：请客','2102');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('吃：烟酒','2103');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('穿：自用','2201');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('穿：礼物','2202');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('住：房租','2301');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('住：水电费','2302');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('行：公共交通','2401');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('行：出租','2402');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('用：学习用品','2501');");
        db.execSQL("INSERT INTO category_map(category_name,category_code) VALUES('用：生活用品','2502');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            onCreate(db);
        }

    }

    /**
     * 执行 sql 语句，返回 Cursor 对象
     *
     * @param sql
     * @param selectionArgs
     * @return
     */
    public Cursor selectCursor(String sql, String[] selectionArgs) {
        return dbConn.rawQuery(sql, selectionArgs);
    }

    /**
     * 执行 sql 语句，返回 List 类型的数据集
     *
     * @param sql
     * @param selectionArgs
     * @return
     */
    public List<Map<String, Object>> selectList(String sql, String[] selectionArgs) {
        Cursor cursor = selectCursor(sql, selectionArgs);
        return cursorToList(cursor);
    }

    /**
     * 将 Cursor 中的数据存放到 List 集合中
     *
     * @param cursor
     * @return
     */
    public List<Map<String, Object>> cursorToList(Cursor cursor) {
        if (cursor == null) {
            return null;
        } else {
            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                Map<String, Object> m = new HashMap<String, Object>();
                int columnCount = cursor.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    m.put(cursor.getColumnName(i), cursor.getString(i));
                }
                list.add(m);
            }
            cursor.close();
            return list;
        }
    }

    /**
     * 执行 insert/update/delete 语句，成功返回 true，失败返回 false
     *
     * @param sql
     * @param bindArgs
     * @return
     */
    public boolean execSQL(String sql, Object[] bindArgs) {
        try {
            dbConn.execSQL(sql, bindArgs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}

