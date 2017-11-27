package com.example.taobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taobao.R;
import com.example.taobao.bean.FragBean1;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 大白 on 2017/10/17.
 */

public class Fragment1Adapter extends RecyclerView.Adapter<Fragment1Adapter.MyViewHolder> {

    private List<FragBean1.DatasBean.ClassListBean> list;
    private Context context;

    public Fragment1Adapter(List<FragBean1.DatasBean.ClassListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fitem1, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv1.setText(list.get(position).getGc_name());
       Picasso.with(context).load(list.get(position).getImage()).into(holder.immg1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv1;
        private final ImageView immg1;

        public MyViewHolder(View itemView) {
        super(itemView);
            tv1 = itemView.findViewById(R.id.tv_1);
            immg1 = itemView.findViewById(R.id.img_1);
        }
}
    //接口回调
        private OnItemClickListener listener;

        public interface OnItemClickListener {
            void onItemClick(int position);
        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }



}
