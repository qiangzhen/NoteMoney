package com.zq.notemoney;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zq.notemoney.bean.OutMoney;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Author:甄强
 * Date:2015/10/9
 * Email:978823884@qq.com
 */
@ContentView(R.layout.activity_edit)
public class EditActivity extends Activity {

    @ViewInject(R.id.img_edit_bcak)
    private ImageView img_back;

    @ViewInject(R.id.img_edit_submit)
    private ImageView img_submit;

    @ViewInject(R.id.et_edit_out_money)
    private EditText et_out;

    @ViewInject(R.id.sp_edit_out_category)
    private Spinner sp_category;

    @ViewInject(R.id.sp_edit_out_sub_category)
    private Spinner sp_subcategory;

    @ViewInject(R.id.btn_edit_submit)
    private Button button;
    private OutMoney outMoney;


    private DbUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ViewUtils.inject(this);
        dbUtils = Config.getUtils(getApplicationContext());
        outMoney = (OutMoney) getIntent().getSerializableExtra("outmoney");


        initViews();
        initEvents();


    }


    private void initEvents() {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();


                finish();
            }
        });


        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                finish();
            }
        });
    }

    private void saveData() {
        long id = outMoney.get_id();

        OutMoney outMoney = new OutMoney();
        outMoney.setMoney(Float.parseFloat(et_out.getText().toString()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
        String date = simpleDateFormat.format(new Date());
        outMoney.setDate(date);

        int categroy = (int) sp_category.getSelectedItemId();
        int sub_category = (int) sp_subcategory.getSelectedItemId();

        outMoney.setCategory(categroy);
        outMoney.setSub_category(sub_category);
        WhereBuilder builder = WhereBuilder.b("_id", "=", id);

        try {
            dbUtils.update(outMoney, builder);
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    private void initViews() {
        et_out.setText(String.valueOf(outMoney.getMoney()));
        ArrayAdapter<String> categoryAdp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Config.categroy);
        sp_category.setAdapter(categoryAdp);

        ArrayAdapter<String> subcategoryAdp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Config.subCategroy[0]);
        sp_subcategory.setAdapter(subcategoryAdp);

        int i = outMoney.getCategory();
        int j = outMoney.getSub_category();

        sp_category.setSelection(i);
        sp_subcategory.setSelection(j);

    }
}
