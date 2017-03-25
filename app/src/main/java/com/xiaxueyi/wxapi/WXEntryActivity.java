package com.xiaxueyi.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xiaxueyi.utils.Constants;


public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
	private IWXAPI mWxApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mWxApi = WXAPIFactory.createWXAPI(this, Constants.APP_ID, true);
		mWxApi.registerApp(Constants.APP_ID);
		mWxApi.handleIntent(getIntent(), this);
	}



	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		setIntent(intent);
		// 注册回调监听事件
		mWxApi.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq baseReq) {
		switch (baseReq.getType()) {
			case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
				Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
				break;
			case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
				Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
		}

	}

	@Override
	public void onResp(BaseResp baseResp) {
		String result = "";

		switch (baseResp.errCode) {
			case BaseResp.ErrCode.ERR_OK:
				result = "errcode_success";
				break;
			case BaseResp.ErrCode.ERR_USER_CANCEL:
				result = "errcode_cancel";
				break;
			case BaseResp.ErrCode.ERR_AUTH_DENIED:
				result = "errcode_deny";
				break;
			default:
				result = "errcode_unknown";
				break;
		}

		Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
	}

}