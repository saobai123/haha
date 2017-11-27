package com.example.taobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taobao.R;
import com.example.taobao.bean.FragBean3;

import java.util.List;

/**
 * Created by 大白 on 2017/10/17.
 */

public class Fragment3Adapter extends RecyclerView.Adapter<Fragment3Adapter.MyViewHolder> {

    private List<FragBean3.DatasBean.ClassListBean> list1;
    private Context context;

    public Fragment3Adapter(List<FragBean3.DatasBean.ClassListBean> list1, Context context) {
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fitem3, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv3.setText(list1.get(position).getGc_name());
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv3;

        public MyViewHolder(View itemView) {
        super(itemView);
            tv3 = itemView.findViewById(R.id.tv_3);
        }
}




}
