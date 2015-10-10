package com.zq.notemoney.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zq.notemoney.Config;
import com.zq.notemoney.R;
import com.zq.notemoney.bean.OutMoney;

import java.util.List;

/**
 * Author:甄强
 * Date:2015/10/9
 * Email:978823884@qq.com
 */
public class OutMoneyAdapter extends BaseAdapter {

    private Context context;
    private List<OutMoney> datas;

    public OutMoneyAdapter(Context context, List<OutMoney> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (datas != null) {
            ret = datas.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return datas.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_out, parent, false);
            holder.tv_category = (TextView) convertView.findViewById(R.id.tv_categroy);
            holder.tv_money = (TextView) convertView.findViewById(R.id.tv_money);
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            convertView.setTag(holder);
        }

        ViewHolder holder1 = (ViewHolder) convertView.getTag();
        OutMoney outMoney = datas.get(position);

        holder1.tv_category.setText(Config.categroy[outMoney.getCategory()]);
        holder1.tv_money.setText(String.valueOf(outMoney.getMoney()));
        holder1.tv_date.setText(outMoney.getDate());

        return convertView;
    }


    class ViewHolder {
        TextView tv_category, tv_money, tv_date;
    }
}
