package com.example.taobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taobao.R;
import com.example.taobao.bean.FindBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 大白 on 2017/10/18.
 */

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.MyViewHolder> {

  private List<FindBean.DatasBean.GoodsListBean> list;
    private Context context;
    private MyViewHolder holder;


    public MyAdapter2(List<FindBean.DatasBean.GoodsListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_find, parent, false);
        holder = new MyViewHolder(view);
        //放在OnCreateViewHolder
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
        holder.tv_find.setText(list.get(position).getGoods_name());
        Picasso.with(context).load(list.get(position).getGoods_image_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv_find;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_find);
            tv_find = itemView.findViewById(R.id.tv_find);
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
