package com.example.taobao.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taobao.R;
import com.example.taobao.bean.FragBean2;
import com.example.taobao.bean.FragBean3;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 大白 on 2017/10/17.
 */

public class Fragment2Adapter extends RecyclerView.Adapter<Fragment2Adapter.MyViewHolder> {

    private List<FragBean2.DatasBean.ClassListBean> list;
    private Context context;

    public Fragment2Adapter(List<FragBean2.DatasBean.ClassListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fitem2, parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv2.setText(list.get(position).getGc_name());

        String id = list.get(position).getGc_id();
        holder.re3.setLayoutManager(new GridLayoutManager(context,3));
        String url="http://169.254.110.146/mobile/index.php?act=goods_class&gc_id="+id;
        OkHttp3Utils.doGet(url, new GsonObjectCallback<FragBean3>() {
            @Override
            public void onUi(FragBean3 fragBean3) {
                List<FragBean3.DatasBean.ClassListBean> list1 = fragBean3.getDatas().getClass_list();
                Fragment3Adapter adapter = new Fragment3Adapter(list1,context);
                 holder.re3.setAdapter(adapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv2;
        private final RecyclerView re3;

        public MyViewHolder(View itemView) {
        super(itemView);
            tv2 = itemView.findViewById(R.id.tv_2);
            re3 = itemView.findViewById(R.id.re3);
        }
}




}
