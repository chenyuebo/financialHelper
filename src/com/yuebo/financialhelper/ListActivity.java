package com.yuebo.financialhelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yuebo.financialhelper.adapter.MyListAdapter;
import com.yuebo.financialhelper.domain.Account;
import com.yuebo.financialhelper.utils.DBUtilsTool;
import com.yuebo.financialhelper.utils.MySQLiteOpenHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public class ListActivity extends Activity {
    @ViewInject(R.id.listView_list)
    private ListView listView_list;

    List<Account> list_total;
    MyListAdapter adapter;
    private DbUtils dbUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ViewUtils.inject(this);
        dbUtils = DBUtilsTool.getDBUtils(this);
        initListView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //dbUtils.close();
    }

    private void initListView() {
        try {
            list_total = dbUtils.findAll(Account.class);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if(null != list_total) {
            adapter = new MyListAdapter(this, list_total);
            listView_list.setAdapter(adapter);
        }
    }
}