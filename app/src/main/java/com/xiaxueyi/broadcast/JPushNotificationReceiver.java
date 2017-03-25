package com.xiaxueyi.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.xiaxueyi.R;
import com.xiaxueyi.utils.Constants;

import cn.jpush.android.api.JPushInterface;

/**
 * @author: xiaxueyi
 * @date: 2016-08-25
 * @time: 11:11
 * @说明: 自定义广播接收器,接收极光推送，不做其他使用
 */
public class JPushNotificationReceiver extends BroadcastReceiver{

    private NotificationManager mNotificationManager=null;
    private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext=context;
        if(mNotificationManager==null){
            mNotificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        }

        if(intent!=null){
            Bundle bundle=intent.getExtras();
            if(JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())){  //用户自定义消息

                //获取用户的消息
                String message=bundle.getString(JPushInterface.EXTRA_MESSAGE);
                String title=bundle.getString(JPushInterface.EXTRA_EXTRA);
                initNotification(message);
                initSendMessage(context,message);


            }
        }
    }


    private void initNotification(String message){
        Notification notification=new Notification();
        notification.icon=R.drawable.ic_launcher;
        RemoteViews contentView = new RemoteViews(mContext.getPackageName(),R.layout.layout_notification_item);
        contentView.setImageViewResource(R.id.iv_logo, R.mipmap.logo_qq);
        contentView.setTextViewText(R.id.title, "不好意思，米有标题");
        contentView.setTextViewText(R.id.content, message);
        notification.contentView = contentView;
//        Intent notificationIntent = new Intent(this,Ma.class);
//        PendingIntent contentIntent = PendingIntent.getActivity(mContext,0,notificationIntent,0);
//        notification.contentIntent = contentIntent;
        //把Notification传递给NotificationManager
        mNotificationManager.notify(0,notification);
    }



    private void initSendMessage(Context context,String message){
        Intent intent=new Intent();
        intent.putExtra(Constants.MESSAGE_FLAG, message);
        intent.setAction(Constants.BROADCAST_RECEIVER_FLAG);
        context.sendBroadcast(intent);

    }
}

