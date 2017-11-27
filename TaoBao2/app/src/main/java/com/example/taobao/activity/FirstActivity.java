package com.example.taobao.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.taobao.R;
import com.example.taobao.api.Api;

public class FirstActivity extends AppCompatActivity {
    private  int i = 3;
    private ImageView img;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                Intent intent = new Intent(FirstActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        Api.init(getApplicationContext());

        Api.edit.putBoolean("isLogin",false).commit();

        img = (ImageView) findViewById(R.id.img);


        new Thread(){
            @Override
            public void run() {
                super.run();
                while (i>0){
                    try {
                        sleep(1000);
                        i--;
                        handler.sendEmptyMessage(i);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

}
