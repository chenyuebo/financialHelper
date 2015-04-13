package com.yuebo.financialhelper.domain;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Foreign;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.util.Date;

/**
 * Created by Administrator on 2015/4/12.
 * Email: 1197421347@qq.com
 */
@Table(name = "account")
public class Account {
    @Id(column = "id")
    private long id;
    @Column(column = "money")
    private double money;
    @Foreign(column = "category_code",foreign = "category_code")
    private Category category;
    @Column(column = "date")
    private String date;
    @Column(column = "detail")
    private String detail;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
