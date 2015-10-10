package com.zq.notemoney.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zq.notemoney.R;

/**
 * Author:甄强
 * Date:2015/10/9
 * Email:978823884@qq.com
 */
public class InMoneyFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inmoney, container, false);

        return view;
    }
}
