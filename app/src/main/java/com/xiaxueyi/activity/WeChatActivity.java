package com.xiaxueyi.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xiaxueyi.R;
import com.xiaxueyi.utils.Constants;

/**
 * @author: xiaxueyi
 * @date: 2016-08-30
 * @time: 13:50
 * @说明: 分享界面
 */
public class WeChatActivity extends BaseActivity implements View.OnClickListener {

    private ImageView mShare=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_we_chat_share);

        initWidget();

    }


    private void initWidget(){
        mShare=(ImageView)super.findViewById(R.id.we_chat_share);
        mShare.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.we_chat_share:

                if(!mIWXAPI.isWXAppInstalled()){
                    Toast.makeText(this,"微信没有安装！",Toast.LENGTH_SHORT).show();
                    return ;
                }
                initShareText();
                break;
        }
    }



    /**
     * 初始化分享的文本
     */
    private void initShareText(){
        //实例化WXTextObject对象
        WXTextObject object=new WXTextObject();
        object.text="今天天气真好，易趣生活";
        //使用WXTextObject 对象初始化 WXMediaMessage 对象

        WXMediaMessage message=new WXMediaMessage();
        message.mediaObject=object;
        //发送文本消息
        message.description="这是微信分享的文本description";


        //构建Rep

        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction = "transaction"+System.currentTimeMillis(); // transaction字段用于唯一标识一个请求
        req.message=message;
        // 发送到聊天界面 —— WXSceneSession
        // 发送到朋友圈 —— WXSceneTimeline
        // 添加到微信收藏 —— WXSceneFavorite
        req.scene = true ? SendMessageToWX.Req.WXSceneTimeline : SendMessageToWX.Req.WXSceneSession;

        //调用API 接口，把数据发送到微信上
        mIWXAPI.sendReq(req);
    }


}

