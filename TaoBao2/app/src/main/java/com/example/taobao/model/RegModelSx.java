package com.example.taobao.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.taobao.api.Api;
import com.example.taobao.bean.RegBean;
import com.example.taobao.finish.RegFinishListener;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 大白 on 2017/10/13.
 */

public class RegModelSx implements RegModelInterface {


  private RegFinishListener listener;

    public RegModelSx(RegFinishListener listener) {
        this.listener = listener;
    }

    @Override
    public void RegLogin(Context context, String regName, String regPwd, String regAnginPwd, String regEmail) {
        if (TextUtils.isEmpty(regName)){
           listener.NameEnpty();
            return;
        }
        if (TextUtils.isEmpty(regPwd)){
            listener.PwdEntype();
            return;
        }
        if (TextUtils.isEmpty(regAnginPwd)){
            listener.AnginPwdEntype();
            return;
        }
        if (TextUtils.isEmpty(regEmail)){
            listener.EmailEnpty();
            return;
        }

        regRequest(context,regName,regPwd,regAnginPwd,regEmail);

    }

    @Override
    public void regRequest(Context context, String regName, String regPwd, String regAgainPwd, String regEmail) {
        Map<String ,String > regParams = new HashMap<String,String>();
        regParams.put("username",regName);
        regParams.put("password",regPwd);
        regParams.put("password_confirm",regAgainPwd);
        regParams.put("email",regEmail);
        regParams.put("client", Api.CLIENT);
        OkHttp3Utils.doPost(Api.REG_PATH,regParams, new GsonObjectCallback<RegBean>() {
            @Override
            public void onUi(RegBean regBean) {
                   if (regBean.getCode()==200){
                       listener.RegSuccess();
                   }else if (regBean.getCode()==400){
                       listener.RegFailed();
                   }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }




}
