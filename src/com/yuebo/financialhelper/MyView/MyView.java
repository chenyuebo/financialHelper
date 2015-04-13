package com.yuebo.financialhelper.MyView;

import android.content.Context;
import android.graphics.*;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by chenyuebo
 * Date: 15-4-10
 * Email:1197421347@qq.com
 */
public class MyView extends View {
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        initTestData();
    }

    private double[] list ;
    private int[] colors;
    private String[] title;
    private void initTestData() {
        list= new double[]{1000.0, 2000.0, 400.0, 400.0, 400.0, 400.0, 400.0};
        title = new String[]{"总收入","总支出","吃","穿","住","用","行"};
    }

    public void setData(double[] data){
        this.list = data;
        this.invalidate();
    }
    // 数据格式 Map<String,double>
    // 1总收入、2总支出
    // 消费统计
    // 支出 吃，穿，住，用，行

    TextPaint textPaint;
    Paint paint;
    Paint circlePaint;
    RectF rectF;
    private void init(Context context) {
        textPaint = new TextPaint();
        textPaint.setTextSize(30);
        paint = new Paint();
        circlePaint = new Paint();
        circlePaint.setColor(Color.WHITE);
        rectF = new RectF(0,0,0,0);
        colors = new int[]{
                Color.rgb(0,205,0)
                ,Color.rgb(130,130,130)
                ,Color.rgb(0,0,205)
                ,Color.rgb(0,130,130)
                ,Color.rgb(130,0,130)
                ,Color.rgb(130,130,0)
                ,Color.rgb(205,0,0),};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // clear cavas
        canvas.drawColor(Color.WHITE);
        int viewWidth = getWidth();
        int viewHeight = getHeight();

        // 绘制 总收入
        String totalIn = title[0] + list[0];
        canvas.drawText(totalIn, 0, textPaint.getTextSize(), textPaint);

        // 绘制总支出
        String totalOut = title[1] + list[1];
        canvas.drawText(totalOut, viewWidth / 2, textPaint.getTextSize(), textPaint);

        // 绘制饼图
        int x = 0;
        int y = 100;
        rectF.set(x, y, x + 300, y+300);
        float startAngle = 0;
        for(int i=2;i<list.length;i++){
            // 画饼图
            float sweepAngle = (float)(list[i] / list[1] * 360);
            paint.setColor(colors[i-2]);
            canvas.drawArc(rectF,startAngle,sweepAngle,true,paint);
            startAngle += sweepAngle;

            //画图例
            paint.setStrokeWidth(20);
            canvas.drawLine(x + 350,y + (i-1) * 40,x + 400,y + (i-1) * 40,paint);
            textPaint.setColor(colors[i-2]);
            canvas.drawText(title[i] + ":" + list[i], x + 420,y + (i-1) * 40,textPaint);
        }
        canvas.drawCircle(150,250,50,circlePaint);
    }
}
