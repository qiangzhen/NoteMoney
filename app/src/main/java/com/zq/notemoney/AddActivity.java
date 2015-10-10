package com.zq.notemoney;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
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

public class AddActivity extends Activity {


    @ViewInject(R.id.et_out_money)
    private EditText money;

    @ViewInject(R.id.sp_out_category)
    private Spinner sp_category;

    @ViewInject(R.id.sp_out_sub_category)
    private Spinner sp_subCategory;

    @ViewInject(R.id.btn_submit)
    private Button submit;

    @ViewInject(R.id.img_bcak)
    private ImageView img_back;

    @ViewInject(R.id.img_submit)
    private ImageView img_sub;

    private String flag;

    private static DbUtils dbUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add);

        ViewUtils.inject(this);
        dbUtils = Config.getUtils(getApplicationContext());
        flag = getIntent().getStringExtra("flag");
        initSpinner();
        initEvents();


    }

    private void initEvents() {

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        sp_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayAdapter<String> temp = new ArrayAdapter<String>(AddActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, Config.subCategroy[position]);
                sp_subCategory.setAdapter(temp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    private void initSpinner() {

        ArrayAdapter<String> categoryAdp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Config.categroy);
        sp_category.setAdapter(categoryAdp);

        ArrayAdapter<String> subcategoryAdp = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, Config.subCategroy[0]);
        sp_subCategory.setAdapter(subcategoryAdp);

    }

    private void saveData() {

        if (money.getText() != null && !money.getText().toString().equals("")) {

            float moneyCount = Float.parseFloat(money.getText().toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd HH:mm");
            String date = simpleDateFormat.format(new Date());
            int categroy = (int) sp_category.getSelectedItemId();
            int sub_category = (int) sp_subCategory.getSelectedItemId();

            OutMoney outMoney = new OutMoney();
            outMoney.setMoney(moneyCount);
            outMoney.setDate(date);
            outMoney.setCategory(categroy);
            outMoney.setSub_category(sub_category);

            try {
                dbUtils.save(outMoney);
                finish();
            } catch (DbException e) {
                e.printStackTrace();
            }


        } else {
            Toast.makeText(this, "请输入金额!", Toast.LENGTH_SHORT).show();
        }


    }
}
