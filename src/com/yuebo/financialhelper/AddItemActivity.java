package com.yuebo.financialhelper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.lidroid.xutils.view.annotation.event.OnFocusChange;
import com.yuebo.financialhelper.utils.FormatUtil;
import com.yuebo.financialhelper.utils.MySQLiteOpenHelper;

import java.util.*;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public class AddItemActivity extends Activity {

    private static final String TAG = "AddItemActivity";

    @ViewInject(R.id.editText_add_date)
    private EditText editText_add_date;
    @ViewInject(R.id.spinner_add_category)
    private Spinner spinner_add_category;
    @ViewInject(R.id.editText_add_money)
    private EditText editText_add_money;
    @ViewInject(R.id.editText_add_detail)
    private EditText editText_add_detail;
    @ViewInject(R.id.btn_add_confirm)
    private Button btn_add_confirm;

    List<Map<String, Object>> spinner_data;
    ArrayAdapter<String> arrayAdapter;
    MySQLiteOpenHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        ViewUtils.inject(this);
        dbHelper = new MySQLiteOpenHelper(this);
        initSpinner();

    }

    @OnClick(R.id.btn_add_confirm)
    public void btnAddConfirm(View view){
        int category_code = getInputCategoryCode();
        String date = getInputDate();
        double money = getInputMoney();
        String detail = getInputDetail();
        if(category_code == -1){
            Toast.makeText(this,"请选择类别",Toast.LENGTH_SHORT).show();
        }else if(false == FormatUtil.isValidDate(date)){
            Toast.makeText(this,"请正确输入时间",Toast.LENGTH_SHORT).show();
        }else if(money == 0){
            Toast.makeText(this,"请输入金额",Toast.LENGTH_SHORT).show();
        }else{
            boolean flag = saveData(date,money,category_code,detail);
            if(flag){
                Toast.makeText(this,"保存成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"保存发生异常",Toast.LENGTH_SHORT).show();
            }
        }

    }

    private boolean saveData(String date,double money,int category_code,String detail){
        String sql = "INSERT INTO journal_account(money,category_code,date,detail) VALUES(?,?,?,?)";
        return dbHelper.execSQL(sql,new Object[]{money,category_code,date,detail});
    }

    @OnFocusChange(R.id.editText_add_date)
    public void onFocusChange(View v, boolean hasFocus){
        final EditText edittext = (EditText)v;
        if(hasFocus) {
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    String mon = monthOfYear < 9 ? "0" +(monthOfYear + 1) : ""+(monthOfYear + 1);
                    String day = dayOfMonth <= 9 ? "0" + dayOfMonth : ""+dayOfMonth;
                    String date = year +"-" + mon +"-" + day;
                    edittext.setText(date);
                }
            },year,month,day);
            dialog.show();
        }else{
            String date = getInputDate();
            boolean flag = FormatUtil.isValidDate(date);
            if(flag == false){
                edittext.setText("日期格式错误，请重新输入");
            }
        }
    }

    private  String getInputDate(){
        String s = editText_add_date.getText().toString();
        return s;
    }

    private int getInputCategoryCode(){
        String s = (String)spinner_add_category.getSelectedItem();
        Log.d(TAG,s);
        for (int i=0;i<spinner_data.size();i++){
            String category_name = (String)spinner_data.get(i).get("category_name");
            if(category_name.equals(s)){
                return Integer.parseInt((String) spinner_data.get(i).get("category_code"));
            }
        }
        return -1;
    }

    private double getInputMoney(){
        String s = editText_add_money.getText().toString();
        if(TextUtils.isEmpty(s)){
            return 0;
        }
        return Double.parseDouble(s);
    }

    private String getInputDetail(){
        return editText_add_detail.getText().toString();
    }

    private void initSpinner() {
        spinner_data = dbHelper.selectList("select id,category_name,category_code from category_map;", null);
        List<String> items = new ArrayList<String>();
        items.add("请选择类别");
        for (int i = 0; i < spinner_data.size(); i++) {
            String s = (String) spinner_data.get(i).get("category_name");
            items.add(s);
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, items);
        spinner_add_category.setAdapter(arrayAdapter);
    }
}