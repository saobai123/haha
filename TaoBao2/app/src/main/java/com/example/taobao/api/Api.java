package com.example.taobao.api;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 大白 on 2017/10/12.
 */

public class Api {

    public static final  String url = "169.254.110.146";
    public static final String CLIENT="android";
    //注册的接口
    public static final String REG_PATH="http://"+url+"/mobile/index.php?act=login&op=register";
    //登录的接口
    public static final String LOGIN_PATH="http://"+url+"/mobile/index.php?act=login";
    public static SharedPreferences preferences;
    public static SharedPreferences.Editor edit;

    public  static  void init(Context context){

        preferences = context.getSharedPreferences("name", Context.MODE_PRIVATE);

        edit = preferences.edit();
    }

}
