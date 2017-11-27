package com.example.taobao.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taobao.R;
import com.example.taobao.activity.AddressActivity;
import com.example.taobao.activity.LoginActivity;
import com.example.taobao.activity.MapActivity;
import com.example.taobao.api.Api;

/**
 * Created by 大白 on 2017/10/10.
 */

public class Fragment05 extends Fragment {


    private ImageView map;
    private TextView tv_add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.item05, null);
        ImageView login_tou = view.findViewById(R.id.login_tou);
        login_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
            }
        });


        tv_add = view.findViewById(R.id.tv_add);
        tv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                startActivity(intent);
            }
        });


        boolean isLogin = Api.preferences.getBoolean("isLogin", false);
        if(isLogin==false){
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }

            TextView tv = view.findViewById(R.id.login_name);
            String name = Api.preferences.getString("username", "");
            String key = Api.preferences.getString("key", "");
            
            tv.setText(name);


        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MapActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }


}
