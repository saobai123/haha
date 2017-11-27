package com.example.taobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taobao.R;
import com.example.taobao.adapter.Fragment1Adapter;
import com.example.taobao.adapter.Fragment2Adapter;
import com.example.taobao.bean.FragBean1;
import com.example.taobao.bean.FragBean2;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 大白 on 2017/10/10.
 */

public class Fragment02 extends Fragment {

    private RecyclerView re1;
    private RecyclerView re2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.item02,container, false);


        re1 = view.findViewById(R.id.re1);
        re2 = view.findViewById(R.id.re2);

        re1.setLayoutManager(new LinearLayoutManager(getActivity()));
        String url="http://169.254.110.146/mobile/index.php?act=goods_class";
        OkHttp3Utils.doGet(url, new GsonObjectCallback<FragBean1>() {
            @Override
            public void onUi(FragBean1 fragBean1) {
                final List<FragBean1.DatasBean.ClassListBean> list = fragBean1.getDatas().getClass_list();
                Fragment1Adapter adapter = new Fragment1Adapter(list,getActivity());
                re1.setAdapter(adapter);

                adapter.setOnItemClickListener(new Fragment1Adapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        String id = list.get(position).getGc_id();
                        getSecond(id);

                    }

                });
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });



        return view;
    }



    public void getSecond(String id) {

        http://169.254.110.146/mobile/index.php?act=goods_class&gc_id=1

        re2.setLayoutManager(new LinearLayoutManager(getActivity()));
        String url="http://169.254.110.146/mobile/index.php?act=goods_class&gc_id="+id;
        OkHttp3Utils.doGet(url, new GsonObjectCallback<FragBean2>() {
            @Override
            public void onUi(FragBean2 fragBean2) {
                List<FragBean2.DatasBean.ClassListBean> list = fragBean2.getDatas().getClass_list();
                Fragment2Adapter adapter = new Fragment2Adapter(list,getActivity());
                re2.setAdapter(adapter);
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });



    }


}
