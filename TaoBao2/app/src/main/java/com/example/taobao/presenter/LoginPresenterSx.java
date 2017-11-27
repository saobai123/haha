package com.example.taobao.presenter;

import android.content.Context;

import com.example.taobao.finish.LoginFinishListener;
import com.example.taobao.model.LoginModelSx;
import com.example.taobao.view.LoginViewInterfacec;

/**
 * Created by 大白 on 2017/10/13.
 */

public class LoginPresenterSx implements LoginPresenterface,LoginFinishListener{

    private LoginViewInterfacec viewInterfacec;
    private final LoginModelSx modelSx;

    public LoginPresenterSx(LoginViewInterfacec viewInterfacec) {
        this.viewInterfacec = viewInterfacec;
        modelSx = new LoginModelSx(this);
    }

    @Override
    public void getData(Context context, String name, String pwd) {
        modelSx.Login(context,name,pwd);
    }


    @Override
    public void Name() {
         viewInterfacec.onName();
    }

    @Override
    public void Pwd() {
    viewInterfacec.onPwd();
    }

    @Override
    public void Success() {
      viewInterfacec.onSuccess();
    }

    @Override
    public void Faild() {
      viewInterfacec.onFaild();
    }
}
