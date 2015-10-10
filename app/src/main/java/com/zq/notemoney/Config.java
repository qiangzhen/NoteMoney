package com.zq.notemoney;

import android.content.Context;

import com.lidroid.xutils.DbUtils;

/**
 * Author:甄强
 * Date:2015/10/9
 * Email:978823884@qq.com
 */
public class Config {


    public static final String[] categroy = new String[]{"饮食", "娱乐", "购物"};

    public static final String[][] subCategroy = new String[][]{new String[]{"三餐", "水果"}, new String[]{"唱歌", "聚餐"}, new String[]{"衣服", "鞋子", "洗化用品"}};


    private static DbUtils dbUtils;

    public static DbUtils getUtils(Context context) {

        if (dbUtils != null) {
            return dbUtils;
        } else {
            dbUtils = DbUtils.create(context, "app.db");
            return dbUtils;
        }

    }

}
