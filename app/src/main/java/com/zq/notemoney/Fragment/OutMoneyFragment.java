package com.zq.notemoney.Fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.zq.notemoney.Adapter.OutMoneyAdapter;
import com.zq.notemoney.Config;
import com.zq.notemoney.EditActivity;
import com.zq.notemoney.R;
import com.zq.notemoney.bean.OutMoney;

import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:甄强
 * Date:2015/10/9
 * Email:978823884@qq.com
 */
public class OutMoneyFragment extends Fragment {

    private static ListView outListView;
    private static ImageView imageView;
    private static DbUtils dbUtils;
    private static Context context;
    private static List<OutMoney> outMoneys;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        dbUtils = Config.getUtils(getActivity().getApplicationContext());
        context = getActivity().getApplicationContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_outmoney, container, false);
        imageView = (ImageView) view.findViewById(R.id.image_outbag);
        outListView = (ListView) view.findViewById(R.id.listview_out);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
        initEvents();

    }

    private void initEvents() {
        outListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showDialog(outMoneys.get(position));


                return true;
            }
        });


        outListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OutMoney outMoney = outMoneys.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), EditActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("outmoney", outMoney);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void showDialog(OutMoney outMoney) {


        OutMoney o = outMoney;
        final long money_id = o.get_id();

        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("提示")
                .setMessage("确认删除?")
                .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WhereBuilder whereBuilder = WhereBuilder.b("_id", "=", money_id);
                        try {
                            dbUtils.delete(OutMoney.class, whereBuilder);
                            Toast.makeText(getActivity().getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                            loadData();
                        } catch (DbException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .create();

        dialog.show();

    }


    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }

    public static void loadData() {

        try {
            outMoneys = dbUtils.findAll(OutMoney.class);

            if (outMoneys == null || outMoneys.size() == 0) {
                List<OutMoney> datas = new ArrayList<OutMoney>();
                OutMoneyAdapter adapter = new OutMoneyAdapter(context, datas);
                outListView.setAdapter(adapter);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.out_bg);
            } else {
                List<OutMoney> datas = dbUtils.findAll(OutMoney.class);
                OutMoneyAdapter adapter = new OutMoneyAdapter(context, datas);
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageResource(R.drawable.add_bg);

                outListView.setAdapter(adapter);

            }

        } catch (DbException e) {
            e.printStackTrace();
        }
    }
}
