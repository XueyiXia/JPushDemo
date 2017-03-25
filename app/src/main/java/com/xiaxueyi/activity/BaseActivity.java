package com.xiaxueyi.activity;

import android.app.Activity;
import android.os.Bundle;

import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xiaxueyi.utils.Constants;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;

import cn.jpush.android.api.JPushInterface;

/**
 * @author: xiaxueyi
 * @date: 2016-08-25
 * @time: 10:14
 * @说明:
 */
public class BaseActivity extends Activity {

    public IWXAPI mIWXAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //初始化微信实例
        mIWXAPI= WXAPIFactory.createWXAPI(this, Constants.APP_ID,true);
    }

    @Override
    protected void onResume() {

        JPushInterface.onResume(this);
        super.onResume();
    }


    @Override
    protected void onPause() {

        JPushInterface.onPause(this);
        super.onPause();
    }
}

