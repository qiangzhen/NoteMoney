package com.zq.notemoney;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zq.notemoney.Fragment.InMoneyFragment;
import com.zq.notemoney.Fragment.OutMoneyFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.btn_add)
    private Button add;
    private List<Fragment> fragments;

    private MyPagerAdapter myPagerAdapter;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);

        initFragment();
        initActionBar();
        initEvents();
    }

    private void initEvents() {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence text = actionBar.getSelectedTab().getText();
                if (text.equals("支出")) {

                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    intent.putExtra("flag", text);
                    startActivity(intent);

                } else {
                    Intent intent = new Intent(MainActivity.this, AddActivity.class);
                    intent.putExtra("flag", text);
                    startActivity(intent);
                }
            }
        });
    }


    private void initActionBar() {
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab = actionBar.newTab();
        ActionBar.Tab tab1 = actionBar.newTab();

        MyTabListener myTabListener = new MyTabListener();
        tab.setTabListener(myTabListener);
        tab1.setTabListener(myTabListener);
        actionBar.addTab(tab.setText("支出"));
        actionBar.addTab(tab1.setText("收入"));

    }

    private void initFragment() {

        fragments = new ArrayList<Fragment>();
        InMoneyFragment inMoneyFragment = new InMoneyFragment();
        OutMoneyFragment outMoneyFragment = new OutMoneyFragment();

        fragments.add(outMoneyFragment);
        fragments.add(inMoneyFragment);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(myPagerAdapter);
        MyPagerListener myPagerListener = new MyPagerListener();
        viewPager.setOnPageChangeListener(myPagerListener);

    }


    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }


        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    class MyTabListener implements ActionBar.TabListener {

        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

        }
    }

    class MyPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            actionBar.setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
