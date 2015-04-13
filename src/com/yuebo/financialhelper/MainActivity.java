package com.yuebo.financialhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.yuebo.financialhelper.domain.Category;
import com.yuebo.financialhelper.utils.DBUtilsTool;

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private DbUtils dbUtils;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewUtils.inject(this);
        dbUtils = DBUtilsTool.getDBUtils(this);

        String[][] categorys = {
                {"收入：工资", "1101"},
                {"收入：外快", "1201"},
                {"吃：日常", "2101"},
                {"吃：请客", "2102"},
                {"吃：烟酒", "2103"},
                {"穿：自用", "2201"},
                {"穿：礼物", "2202"},
                {"住：房租", "2301"},
                {"住：水电费", "2302"},
                {"行：公共交通", "2401"},
                {"行：出租", "2402"},
                {"用：学习用品", "2501"},
                {"用：生活用品", "2502"}
        };

        for (int i = 0; i < categorys.length; i++) {
            Category category = new Category();
            category.setCategory_name(categorys[i][0]);
            category.setCategory_code(Long.parseLong(categorys[i][1]));
            try {
                dbUtils.save(category);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        dbUtils.close();
    }

    @OnClick(R.id.btn_main_add)
    public void btnAddItem(View view) {
        Log.d(TAG, "btn_main_add");
        Intent intent = new Intent(this, AddItemActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_view)
    public void btnViewItem(View view) {
        Log.d(TAG, "btn_main_add");
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_main_statistic)
    public void btnStatistic(View view) {
        Log.d(TAG, "btn_main_add");
        Intent intent = new Intent(this, StatisticActivity.class);
        startActivity(intent);
    }
}
