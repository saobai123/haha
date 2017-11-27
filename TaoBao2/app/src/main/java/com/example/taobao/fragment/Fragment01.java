package com.example.taobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taobao.R;
import com.example.taobao.activity.FindActivity;
import com.example.taobao.adapter.MyAdapter01;
import com.google.zxing.WeChatCaptureActivity;

/**
 * Created by 大白 on 2017/10/10.
 */

public class Fragment01 extends Fragment implements View.OnClickListener {

    private ImageView img_find;
    private RecyclerView recyclerView;
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.item01, null);

        //搜索框
        getFind(view);

        imageView=(ImageView) view.findViewById(R.id.imageView);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        MyAdapter01 adapter = new MyAdapter01(getActivity());
        recyclerView.setAdapter(adapter);
        imageView.setOnClickListener(this);

        return view;
    }





    //点击跳转到搜索页面
    private void getFind(View view) {
        img_find = (ImageView) view.findViewById(R.id.img_find);

        img_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FindActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView:
                Intent intent = new Intent(getActivity(), WeChatCaptureActivity.class);
                startActivity(intent);
                break;
        }
    }
}
