package com.xiaxueyi.broadcast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xiaxueyi.R;
import com.xiaxueyi.utils.Constants;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * @author: xiaxueyi
 * @date: 2016-08-25
 * @time: 16:30
 * @说明: 广播接收器
 */
public class MessageReceiver extends BroadcastReceiver{

    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext=context;

        if(intent!=null){
            Bundle bundle=intent.getExtras();
            if(intent.getAction().equals(Constants.BROADCAST_RECEIVER_FLAG)){
                String message=bundle.getString(Constants.MESSAGE_FLAG);
                initBasicPushNotificationBuilder();
                initCustomPushNotificationBuilder(message);

            }
        }
    }



    /**
     * 自定义消息通知栏，极光提供的
     */
    private void initCustomPushNotificationBuilder(String message){
        CustomPushNotificationBuilder builder=new CustomPushNotificationBuilder(
                mContext,
                R.layout.layout_notification_item,
                R.id.iv_logo,
                R.id.title,
                R.id.content);

        builder.layoutIconDrawable = R.drawable.ic_launcher;
        builder.developerArg0 = message;
        JPushInterface.setPushNotificationBuilder(2, builder);
    }


    /**
     * 初始化消息提醒的基本设置
     */
    private void initBasicPushNotificationBuilder(){
        BasicPushNotificationBuilder builder=new BasicPushNotificationBuilder(mContext);
        builder.statusBarDrawable = R.drawable.ic_launcher;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为点击后自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND;  //设置为铃声（ Notification.DEFAULT_SOUND）或者震动（ Notification.DEFAULT_VIBRATE）
        JPushInterface.setPushNotificationBuilder(1, builder);

    }
}

