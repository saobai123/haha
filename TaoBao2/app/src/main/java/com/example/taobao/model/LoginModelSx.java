package com.example.taobao.model;

import android.content.Context;
import android.text.TextUtils;

import com.example.taobao.api.Api;
import com.example.taobao.bean.RegBean;
import com.example.taobao.finish.LoginFinishListener;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 大白 on 2017/10/13.
 */

public class LoginModelSx implements LoginModelInterface {


    private LoginFinishListener listener;

    public LoginModelSx(LoginFinishListener listener) {
        this.listener = listener;
    }

    @Override
    public void Login(Context context, String name, String pwd) {
        if (TextUtils.isEmpty(name)){
          listener.Name();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            listener.Pwd();
            return;
        }

        Request(context,name, pwd);


    }


    @Override
    public void Request(Context context, final String name, String pwd) {
        Map<String ,String> regParams = new HashMap<String,String>();
        regParams.put("username",name);
        regParams.put("password",pwd);
        regParams.put("client", Api.CLIENT);
        OkHttp3Utils.doPost(Api.LOGIN_PATH,regParams, new GsonObjectCallback<RegBean>() {
            @Override
            public void onUi(RegBean regBean) {
                if (regBean.getCode()==200){

                    String username = regBean.getDatas().getUsername();
                    String key = regBean.getDatas().getKey();
                    Api.edit.putString("username",username);
                    Api.edit.putString("key",key);
                    Api.edit.putBoolean("isLogin",true);
                    Api.edit.commit();
                    listener.Success();


                }else if (regBean.getCode()==400){
                    listener.Faild();
                }
            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });
    }





}
