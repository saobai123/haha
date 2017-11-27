package com.example.taobao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taobao.R;
import com.example.taobao.api.Api;
import com.example.taobao.presenter.LoginPresenterSx;
import com.example.taobao.view.LoginViewInterfacec;

public class Login2Activity extends AppCompatActivity implements View.OnClickListener,LoginViewInterfacec{

    private EditText et_name;
    private EditText et_pwd;
    private Button bt_login;
    private LoginPresenterSx presenterSx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(this);

        presenterSx = new LoginPresenterSx(this);
    }


    @Override
    public void onClick(View v) {

      presenterSx.getData(Login2Activity.this,et_name.getText().toString(),et_pwd.getText().toString());

    }


    @Override
    public void onName() {
            et_name.setError("用户名不能为空");
    }

    @Override
    public void onPwd() {
        et_name.setError("密码不能为空");
    }

    @Override
    public void onSuccess() {
       Api.edit.putBoolean("isLogin",true).commit();
        setResult(2);
        finish();
    }

    @Override
    public void onFaild() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }
}
