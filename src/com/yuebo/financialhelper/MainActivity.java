package com.yuebo.financialhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewUtils.inject(this);
    }

    @OnClick(R.id.btn_main_add)
    public void btnAddItem(View view){
        Log.d(TAG,"btn_main_add");
        Intent intent = new Intent(this,AddItemActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_view)
    public void btnViewItem(View view){
        Log.d(TAG,"btn_main_add");
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_statistic)
    public void btnStatistic(View view){
        Log.d(TAG,"btn_main_add");
        Intent intent = new Intent(this,StatisticActivity.class);
        startActivity(intent);
    }
}
