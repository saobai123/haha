package com.example.taobao.presenter;

import android.content.Context;

import com.example.taobao.finish.RegFinishListener;
import com.example.taobao.model.RegModelSx;
import com.example.taobao.view.RegViewInterface;

/**
 * Created by 大白 on 2017/10/13.
 */

public class RegPresenterSx implements RegPresenterInterface,RegFinishListener{


    private RegViewInterface  viewInterface;
    private final RegModelSx modelSx;

    public RegPresenterSx(RegViewInterface viewInterface) {
        this.viewInterface = viewInterface;
        modelSx = new RegModelSx(this);
    }

    @Override
    public void RegData(Context context, String regName, String regPwd, String regAnginPwd, String regEmail) {
            modelSx.RegLogin(context,regName,regPwd,regAnginPwd,regEmail);
    }


    @Override
    public void NameEnpty() {
         viewInterface.onRegNameEmpty();
    }

    @Override
    public void PwdEntype() {
       viewInterface.onRegPwdEmpty();
    }

    @Override
    public void AnginPwdEntype() {
       viewInterface.onRegAgainEmpty();
    }

    @Override
    public void EmailEnpty() {
     viewInterface.onRegEmailEmpty();
    }

    @Override
    public void RegSuccess() {
      viewInterface.onRegSucceed();
    }

    @Override
    public void RegFailed() {
      viewInterface.onRegFailed();
    }
}
