package com.yuebo.financialhelper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yuebo.financialhelper.adapter.MyListAdapter;
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

    List<Map<String,Object>> list_total;
    MySQLiteOpenHelper dbHelper;
    MyListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ViewUtils.inject(this);
        dbHelper = new MySQLiteOpenHelper(this);
        initListView();
    }

    private void initListView(){
        String sql = "select id,money,date,category_code,detail from journal_account;";
        list_total = dbHelper.selectList(sql,null);
        adapter = new MyListAdapter(this,list_total);
        listView_list.setAdapter(adapter);
    }
}