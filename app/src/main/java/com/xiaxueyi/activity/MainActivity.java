package com.xiaxueyi.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.xiaxueyi.R;
import com.xiaxueyi.broadcast.MessageReceiver;
import com.xiaxueyi.utils.ExampleUtil;
import com.xiaxueyi.wxapi.WXEntryActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private static final String TAG="MainActivity";

    private ImageView mFloatingActionButton=null;

    private ImageView mTestImage=null;


    private MessageReceiver mMessageReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);

        initWidget();


        initGetDisplay();


    }



    private void initGetDisplay(){
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float density = displayMetrics.density; //屏幕密度
        int densityDpi = displayMetrics.densityDpi;//屏幕密度dpi
        int heightPixels = displayMetrics.heightPixels;//屏幕高度的像素
        int widthPixels = displayMetrics.widthPixels;//屏幕宽度的像素
        float scaledDensity = displayMetrics.scaledDensity;//字体的放大系数
        float xdpi = displayMetrics.xdpi;//宽度方向上的dpi
        float ydpi = displayMetrics.ydpi;//高度方向上的dpi
        Log.i(TAG, "density = " + density);
        Log.i(TAG, "densityDpi = " + densityDpi);
        Log.i(TAG, "scaledDensity = " + scaledDensity);
        Log.i(TAG, "Screen resolution = " + widthPixels + "×" + heightPixels);
        Log.i(TAG, "xdpi = " + xdpi);
        Log.i(TAG, "ydpi = " + ydpi);
        mTestImage.post(new Runnable() {
//            @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
            @Override
            public void run() {
                Drawable oldDrawable=getResources().getDrawable(R.drawable.test_image);
                BitmapDrawable drawable = (BitmapDrawable)oldDrawable;
                if (drawable != null) {
                    Bitmap bitmap = drawable.getBitmap();
                    Log.i(TAG, "bitmap width = " + bitmap.getWidth() + " bitmap height = " + bitmap.getHeight());
                    Log.i(TAG, "bitmap size = " + bitmap.getByteCount());//获取bitmap的占用内存
                    Log.i(TAG, "imageView width = " + mTestImage.getWidth() + " imageView height = " + mTestImage.getHeight());
                    Log.i(TAG, "imageView scaleType = " + mTestImage.getScaleType());
                }
            }
        });
    }





    /**
     * 初始化组件
     */
    private void initWidget(){
        mFloatingActionButton=(ImageView)findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(this);

        mTestImage=(ImageView)findViewById(R.id.test_image);


        String udid =  ExampleUtil.getImei(getApplicationContext(), "");
        String appKey = ExampleUtil.getAppKey(getApplicationContext());
        if (null == appKey) appKey = "AppKey异常";
        String packageName =  getPackageName();
        String deviceId = ExampleUtil.getDeviceId(getApplicationContext());
        String versionName =  ExampleUtil.GetVersion(getApplicationContext());

    }



    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
//        filter.addAction(MESSAGE_RECEIVED_ACTION);
        registerReceiver(mMessageReceiver, filter);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){     //微信分享
            case R.id.fab:
                Intent intent=new Intent();
                intent.setClass(this,WeChatActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        unregisterReceiver(mMessageReceiver);
        super.onDestroy();
    }
}
