package com.hdlink.online.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.hdlink.online.R;
import com.hdlink.online.Utils;
import com.hdlink.online.net.NetProtocol;
import com.hdlink.online.net.NetService;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * Created by zhongqiling on 14-5-28.
 */
public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";
    EditText userText;
    EditText pswText;
    Button loginbtn;
    final Activity self = this;
    NetService service;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.login_activity);
        userText = (EditText) findViewById(R.id.login_user_input);
        pswText = (EditText) findViewById(R.id.login_psw_input);
        loginbtn = (Button) findViewById(R.id.login_confirm_btn);

//        if (Utils.getVersionType(this).equals("driver")) {
//            AnalyticsConfig.setAppkey("53c5184156240bb4720f0f39");
//        } else {
//            //TODO 货主版
//        }

        service = new NetService(this);
        setDefaultLayout("登录", WITH_NONE);

//        Utils.init(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(TAG); //统计页面
//        MobclickAgent.onResume(this,Bridge.shared().UMENG_KEY,Bridge.shared().CHANNEL_STRING);          //统计时长
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(TAG); // 保证 onPageEnd 在onPause 之前调用,因为 onPause 中会保存信息
//        MobclickAgent.onPause(this);
//        MobclickAgent.onResume(this, MapStatic.YOU_MENG_APPK, MapStatic.ChannelId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_register){
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        // Handle presses on the action bar items
//        switch (item.getItemId()) {
//            case R.id.menu_register:
//                Intent intent = new Intent(this, RegisterActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
//                break;
//        }
        return super.onOptionsItemSelected(item);
    }

    private boolean phonenumVerified(String phonenum) {
        if (phonenum.length() == 11) {
            return true;
        } else {
            return false;
        }
    }

    public void login() {

//        if (Utils.getVersionType(this).equals("driver")) {
//            EventLogUtils.EventLog(this, EventLogUtils.tthcc_driver_login_btn);
//        } else {
//            EventLogUtils.EventLog(this, EventLogUtils.tthcc_owner_login_btn);
//
//        }
        if (userText.getText() == null || pswText.getText() == null) {
            return;
        }
        String userTextStr = userText.getText().toString();
        String pswTextStr = pswText.getText().toString();
        String encodePSW = "";

        if (userTextStr.length() == 0) {
            Utils.showToast(this, "请输入手机号码");
            return;
        }

        if (!phonenumVerified(userTextStr)) {
            Utils.showToast(this, "请输入有效的手机号码");
            return;
        }

        if (pswTextStr.length() == 0) {
            Utils.showToast(this, "请输入密码");
            return;
        }

        encodePSW = encode(pswTextStr);

        new NetService(self).login(userTextStr, encodePSW, new NetService.NetCallBack() {
            @Override
            public void onCall(NetProtocol result) {
                if(result.code == NetProtocol.SUCCESS){
                    Intent intent = new Intent(self, MainActivity.class);
                    self.startActivity(intent);
                    finish();
                }
            }
        });

    }

    private String encode(String str){
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.reset();

            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }

        return md5StrBuff.toString();

    }
}