package com.yuebo.financialhelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.DbModelSelector;
import com.lidroid.xutils.db.table.DbModel;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnFocusChange;
import com.yuebo.financialhelper.MyView.MyView;
import com.yuebo.financialhelper.domain.Account;
import com.yuebo.financialhelper.utils.DBUtilsTool;
import com.yuebo.financialhelper.utils.DateUtil;
import com.yuebo.financialhelper.utils.FormatUtil;
import com.yuebo.financialhelper.utils.MySQLiteOpenHelper;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public class StatisticActivity extends Activity {

    private static final String TAG = "StatisticActivity";

    @ViewInject(R.id.myView_statistic)
    private MyView myView_statistic;
    //    @ViewInject(R.id.edittext_start_date)
    //    private EditText edittext_start_date;
    //    @ViewInject(R.id.edittext_end_date)
    //    private EditText edittext_end_date;
    @ViewInject(R.id.datePicker_start)
    private DatePicker datePicker_start;
    @ViewInject(R.id.datePicker_end)
    private DatePicker datePicker_end;

    String start_date = "1899-01-01";
    String end_date = "2015-04-30";
    String[] viewNames = {"1%", "2%", "21%", "22%", "23%", "25%", "24%"};
    private DbUtils dbUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        ViewUtils.inject(this);
        dbUtils = DBUtilsTool.getDBUtils(this);
        refreshData(start_date, end_date);

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        datePicker_start.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String mon = monthOfYear < 9 ? "0" + (monthOfYear + 1) : "" + (monthOfYear + 1);
                String day = dayOfMonth <= 9 ? "0" + dayOfMonth : "" + dayOfMonth;
                start_date = year + "-" + mon + "-" + day;
                Log.d(TAG, "更新数据了");
                refreshData(start_date, end_date);
            }
        });

        datePicker_end.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String mon = monthOfYear < 9 ? "0" + (monthOfYear + 1) : "" + (monthOfYear + 1);
                String day = dayOfMonth <= 9 ? "0" + dayOfMonth : "" + dayOfMonth;
                end_date = year + "-" + mon + "-" + day;
                Log.d(TAG, "更新数据了");
                refreshData(start_date, end_date);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        dbUtils.close();
    }

    private void refreshData(String start_date, String end_date) {
        double[] datas = new double[viewNames.length];
        for (int i = 0; i < viewNames.length; i++) {
            datas[i] = getTotal(i, start_date, end_date);
        }

        myView_statistic.setData(datas);
    }

//    @OnFocusChange({R.id.edittext_start_date,R.id.edittext_end_date})
//    public void onFocusChange(View v, boolean hasFocus){
//        final EditText edittext = (EditText)v;
//        if(hasFocus) {
//            Calendar calendar = Calendar.getInstance();
//            int day = calendar.get(Calendar.DAY_OF_MONTH);
//            int month = calendar.get(Calendar.MONTH);
//            int year = calendar.get(Calendar.YEAR);
//            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//                @Override
//                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                    String mon = monthOfYear < 9 ? "0" +(monthOfYear + 1) : ""+(monthOfYear + 1);
//                    String day = dayOfMonth <= 9 ? "0" + dayOfMonth : ""+dayOfMonth;
//                    String date = year +"-" + mon +"-" + day;
//                    edittext.setText(date);
//                }
//            },year,month,day);
//            dialog.show();
//        }else{
//            String date = edittext.getText().toString();
//            boolean flag = FormatUtil.isValidDate(date);
//            if(flag == false){
//                edittext.setText("日期格式错误，请重新输入");
//            }
//        }
//    }

//    @OnClick(R.id.btn_statistic)
//    public void btnStatistic(View view){
//        String start = edittext_start_date.getText().toString();
//        String end = edittext_end_date.getText().toString();
//        if(!FormatUtil.isValidDate(start)){
//            Toast.makeText(this,"开始日期格式错误",Toast.LENGTH_SHORT).show();
//        }else if(!FormatUtil.isValidDate(end)){
//            Toast.makeText(this,"结束日期格式错误",Toast.LENGTH_SHORT).show();
//        }else{
//            Log.d(TAG,"更新数据了");
//            refreshData(start,end);
//        }
//    }


    private double getTotal(int index, String startDate, String endDate) {
        DbModelSelector modelSelector = DbModelSelector
                .from(Account.class)
                .select("sum(money) as sum")
                .where("category_code", "like", viewNames[index])
                .and("date", ">=", startDate)
                .and("date", "<=", end_date);
        DbModel dbModelFirst = null;
        try {
            dbModelFirst = dbUtils.findDbModelFirst(modelSelector);
        } catch (DbException e) {
            e.printStackTrace();
        }
        if (dbModelFirst != null) {
            String sum = dbModelFirst.getString("sum");
            return sum == null ? 0 : Double.parseDouble(sum);
        }
        return 0;
    }
}