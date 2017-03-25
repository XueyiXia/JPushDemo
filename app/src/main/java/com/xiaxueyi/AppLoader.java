package com.xiaxueyi;

import android.app.Application;

import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xiaxueyi.utils.Constants;

import cn.jpush.android.api.JPushInterface;

/**
 * @author: xiaxueyi
 * @date: 2016-08-25
 * @time: 10:10
 * @说明:
 */
public class AppLoader extends Application{

    public AppLoader mInstance;

    public AppLoader getInstance(){
        if(mInstance==null){
            mInstance=new AppLoader();
        }

        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        this.mInstance=this;

        initJPush();

    }


    /**
     * 初始化极光推送
     */
    private void initJPush(){
        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}

