package com.example.taobao.model;

import android.content.Context;

/**
 * Created by 大白 on 2017/10/13.
 */

public interface LoginModelInterface {

    void  Login(Context context, String name, String pwd);
    void  Request(Context context, String name, String pwd);

}
