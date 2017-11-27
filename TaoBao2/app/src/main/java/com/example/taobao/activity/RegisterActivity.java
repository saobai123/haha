package com.example.taobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taobao.R;
import com.example.taobao.presenter.RegPresenterSx;
import com.example.taobao.view.RegViewInterface;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,RegViewInterface {

    private EditText et_regName;
    private EditText et_regPwd;
    private EditText et_regAgainPwd;
    private EditText et_regEmail;
    private Button bt_regCommit;
    private RegPresenterSx presenterSx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        presenterSx = new RegPresenterSx(this);

    }

    private void initView() {
        et_regName = (EditText) findViewById(R.id.et_regName);
        et_regPwd = (EditText) findViewById(R.id.et_regPwd);
        et_regAgainPwd = (EditText) findViewById(R.id.et_regAgainPwd);
        et_regEmail = (EditText) findViewById(R.id.et_regEmail);
        bt_regCommit = (Button) findViewById(R.id.bt_regCommit);

        bt_regCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_regCommit:
              presenterSx.RegData(RegisterActivity.this,et_regName.getText().toString(),
                      et_regPwd.getText().toString(),et_regAgainPwd.getText().toString(),
                      et_regEmail.getText().toString()
                      );
                break;
        }
    }


    @Override
    public void onRegNameEmpty() {
        et_regName.setError("注册用户名不能为空");
    }

    @Override
    public void onRegPwdEmpty() {
        et_regPwd.setError("注册密码不能为空");
    }

    @Override
    public void onRegAgainEmpty() {
        et_regAgainPwd.setError("确认密码不能为空");
    }

    @Override
    public void onRegEmailEmpty() {
        et_regEmail.setError("注册邮箱不能为空");
    }

    @Override
    public void onRegSucceed() {
        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RegisterActivity.this,Login2Activity.class));
    }

    @Override
    public void onRegFailed() {
        Toast.makeText(RegisterActivity.this,"注册失败",Toast.LENGTH_SHORT).show();
    }
}
