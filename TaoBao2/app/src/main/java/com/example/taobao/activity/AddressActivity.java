package com.example.taobao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taobao.R;
import com.example.taobao.api.Api;
import com.example.taobao.bean.addBean;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

public class AddressActivity extends AppCompatActivity {

    private EditText add_name;
    private EditText add_phone;
    private EditText add_city;
    private EditText add_area;
    private EditText add_add;
    private EditText add_info;
    private TextView tv_rem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


        initView();



    }

    private void initView() {

        add_name = (EditText) findViewById(R.id.add_name);
        add_phone = (EditText) findViewById(R.id.add_phone);
        add_city = (EditText) findViewById(R.id.add_city);
        add_area = (EditText) findViewById(R.id.add_area);
        add_add = (EditText) findViewById(R.id.add_add);
        add_info = (EditText) findViewById(R.id.add_info);
        tv_rem = (TextView) findViewById(R.id.tv_rem);

        tv_rem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url="http://169.254.110.146/mobile/index.php?act=member_address&op=address_add";
                Map<String,String> mm = new HashMap<String,String>();
                String key = Api.preferences.getString("key", "");
                mm.put("key",key);
                mm.put("true_name",add_name.getText().toString());
                mm.put("mob_phone",add_phone.getText().toString());
                mm.put("city_id",add_city.getText().toString());
                mm.put("area_id",add_area.getText().toString());
                mm.put("address",add_add.getText().toString());
                mm.put("area_info",add_info.getText().toString());
                OkHttp3Utils.doPost(url, mm, new GsonObjectCallback<addBean>() {
                    @Override
                    public void onUi(addBean addBean) {
                        if (addBean.getCode() == 200){
                            Toast.makeText(AddressActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });



            }
        });




    }




}
