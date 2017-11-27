package com.example.taobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taobao.R;
import com.example.taobao.api.Api;
import com.example.taobao.bean.Bean1;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 大白 on 2017/10/12.
 */

public class MyAdapter011 extends RecyclerView.Adapter<MyAdapter011.MyViewHolder11> {

    private List<Bean1.DatasBean.GoodsCommendListBean> list1;
    private Context context;

    public MyAdapter011(List<Bean1.DatasBean.GoodsCommendListBean> list1, Context context) {
        this.list1 = list1;
        this.context = context;
    }

    @Override
    public MyViewHolder11 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item011, parent, false);
        MyViewHolder11 holder11 = new MyViewHolder11(view);
        return holder11;
    }

    @Override
    public void onBindViewHolder(MyViewHolder11 holder, int position) {
        String goods_image_url = list1.get(position).getGoods_image_url();
        StringBuffer buffer = new StringBuffer(goods_image_url);
        StringBuffer replace = buffer.replace(7,16, Api.url);

          ViewGroup.LayoutParams params = holder.img11.getLayoutParams();
                  if(position == 0){
                      params.height = 200;
                  }else{
                      params.height = 300;
                  }
                  holder.img11.setLayoutParams(params);


        Picasso.with(context).load(replace.toString()).into(holder.img11);


              holder.tv_title.setText(list1.get(position).getGoods_name());
        holder.tv_price.setText(list1.get(position).getGoods_promotion_price()+"");

    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class MyViewHolder11 extends RecyclerView.ViewHolder {

        private final ImageView img11;
        private final TextView tv_title;
        private final TextView tv_price;


        public MyViewHolder11(View itemView) {
            super(itemView);
            img11 = itemView.findViewById(R.id.img_test);
            tv_title = itemView.findViewById(R.id.tv_tetle);
            tv_price = itemView.findViewById(R.id.tv_price);

        }
    }
}
