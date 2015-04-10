package com.yuebo.financialhelper.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.yuebo.financialhelper.R;
import com.yuebo.financialhelper.utils.FormatUtil;
import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public class MyListAdapter extends BaseAdapter {

    private static final String TAG = "MyListAdapter";
    private Context context;
    private List<Map<String,Object>> list;

    public MyListAdapter(Context context, List<Map<String,Object>> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list,null);
            viewHolder = new ViewHolder();
            viewHolder.textView_date = (TextView) convertView.findViewById(R.id.textView_money);
            viewHolder.textView_money = (TextView) convertView.findViewById(R.id.textView_date);
            viewHolder.imageView_category = (ImageView) convertView.findViewById(R.id.imageView_category);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Map<String,Object> m = list.get(position);
        Log.d(TAG,m.toString());
        String date= (String)m.get("date");
        String category_code = (String)m.get("category_code");
        String money = (String)m.get("money");

        if(category_code.startsWith("1")){
            viewHolder.imageView_category.setImageResource(R.drawable.up);
        }else if(category_code.startsWith("2")){
            viewHolder.imageView_category.setImageResource(R.drawable.down);
        }else{
            viewHolder.imageView_category.setImageBitmap(null);
        }

        viewHolder.textView_money.setText(money);
        viewHolder.textView_date.setText(date);

        return convertView;
    }

    class ViewHolder{
        TextView textView_money;
        TextView textView_date;
        ImageView imageView_category;
    }
}
