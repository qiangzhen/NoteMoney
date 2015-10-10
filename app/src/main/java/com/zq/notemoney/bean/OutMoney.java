package com.zq.notemoney.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

import java.io.Serializable;

/**
 * Author:甄强
 * Date:2015/10/9
 * Email:978823884@qq.com
 */
@Table(name = "outmoney")
public class OutMoney implements Serializable {

    @Id
    private long _id;

    @Column
    private String date;

    @Column
    private float money;

    @Column
    private int category;

    @Column
    private int sub_category;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getSub_category() {
        return sub_category;
    }

    public void setSub_category(int sub_category) {
        this.sub_category = sub_category;
    }
}
