package com.example.taobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taobao.R;
import com.example.taobao.api.Api;
import com.example.taobao.bean.DataBean;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView car;
    private String goods_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String img = intent.getStringExtra("img");
        String price = intent.getStringExtra("price");
        goods_id = intent.getStringExtra("goods_id");

        TextView tv1= (TextView) findViewById(R.id.tv1_data);
        TextView tv2 = (TextView) findViewById(R.id.tv1_price);
        ImageView imgg = (ImageView) findViewById(R.id.img_data);

        tv1.setText(name);
        tv2.setText(price);
        Picasso.with(DataActivity.this).load(img).into(imgg);


        car = (TextView) findViewById(R.id.car);
        car.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

           switch (v.getId()){
               case R.id.car:

                   String url="http://169.254.110.146/mobile/index.php?act=member_cart&op=cart_add";
                   Map<String,String> map = new HashMap<String,String>();
                   map.put("key", Api.preferences.getString("key", ""));
                   map.put("goods_id",goods_id);
                   map.put("quantity","1");

                   //OkHttp3Utils.doPost(url,map,);
                   OkHttp3Utils.doPost(url, map, new GsonObjectCallback<DataBean>() {
                       @Override
                       public void onUi(DataBean dataBean) {
                           if(dataBean.getCode() == 200){
                               Toast.makeText(DataActivity.this,"添加成功",Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailed(Call call, IOException e) {

                       }
                   });

                   break;
           }

    }



}
