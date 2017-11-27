package com.example.taobao.model;

import android.content.Context;

/**
 * Created by 大白 on 2017/10/13.
 */

public interface RegModelInterface {

    void RegLogin(Context context ,String regName,String regPwd,String regAnginPwd,String regEmail);
    void regRequest(Context context,String regName, String regPwd, String regAgainPwd, String regEmail);
}
