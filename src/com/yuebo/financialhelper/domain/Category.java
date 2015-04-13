package com.yuebo.financialhelper.domain;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2015/4/12.
 * Email: 1197421347@qq.com
 */
@Table(name = "category_map")
public class Category {
    @Id(column = "id")
    private long id;
    @Column(column = "category_name")
    private String category_name;
    @Column(column = "category_code")
    private long category_code;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public long getCategory_code() {
        return category_code;
    }

    public void setCategory_code(long category_code) {
        this.category_code = category_code;
    }

    @Override
    public String toString() {
        return category_name;
    }
}
