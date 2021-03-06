package com.hdlink.online.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by zhongqiling on 14-5-28.
 */
public class RegisterActivity extends BaseActivity {
    private static final String TAG = "RegisterActivity";
    EditText phoneNumEdit;
    EditText regCodeEdit;
    Button registerBtn;
    Button sendRegCodeBtn;
    NetService mService;
    final BaseActivity self = this;

    int second = 60;
    private Timer timer = new Timer();
    private TimerTask task;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            //要做的事情
            if (second <= 0) {
                timer.cancel();
                second = 60;
                sendRegCodeBtn.setEnabled(true);
                sendRegCodeBtn.setText("获取验证码");

            } else {

                sendRegCodeBtn.setText("重新发送(" + String.valueOf(second--) + ")");
                sendRegCodeBtn.setEnabled(false);
            }
            super.handleMessage(msg);
        }
    };


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
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        init();
    }


    private void init() {
        setDefaultLayout("注册", WITH_NONE);
        phoneNumEdit = (EditText) findViewById(R.id.phonenum_edittext);
        regCodeEdit = (EditText) findViewById(R.id.regcode_edittext);
        registerBtn = (Button) findViewById(R.id.register_btn);
        sendRegCodeBtn = (Button) findViewById(R.id.send_reg_code);

        mService = new NetService(this);
        initAppService();
    }

    public void sendRegCode(View v) {

//        if (Utils.getVersionType(self).equals("driver")) {
//            EventLogUtils.EventLog(self, EventLogUtils.tthcc_driver_getRegCode);
//        } else {
//            EventLogUtils.EventLog(self, EventLogUtils.tthcc_owner_getRegCode);
//        }

        if (phoneNumEdit.getText() == null)
            return;

        String phonenum = phoneNumEdit.getText().toString();
        if (phonenum.length() <= 0) {
            Utils.showToast(this, "请输入手机号码");
            return;
        }

        if (phonenum.length() != 11) {
            //TODO 删掉手机号码，聚焦
            Utils.showToast(this, "手机号码格式错误，请重新输入");
            return;
        }

        task = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Message message = new Message();
                message.what = 1;
                handler.sendMessage(message);
            }
        };
        timer = new Timer();
        timer.schedule(task, 1000, 1000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        if(item.getItemId()== R.id.menu_login){
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                ：例如现在的栈情况为：A B C D 。D此时通过intent跳转到B，如果这个intent添加FLAG_ACTIVITY_CLEAR_TOP 标记，则栈情况变为：A B。如果没有添加这个标记，则栈情况将会变成：A B C D B。
            startActivity(intent);
        }
//        switch (item.getItemId()) {
//            case R.id.menu_login:
//                Intent intent = new Intent(this, LoginActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                ：例如现在的栈情况为：A B C D 。D此时通过intent跳转到B，如果这个intent添加FLAG_ACTIVITY_CLEAR_TOP 标记，则栈情况变为：A B。如果没有添加这个标记，则栈情况将会变成：A B C D B。
//                startActivity(intent);
//                break;
//        }
        return super.onOptionsItemSelected(item);
    }

    public void verifyRegCode(View v) {

//        if (Utils.getVersionType(self).equals("driver")) {
//            EventLogUtils.EventLog(self, EventLogUtils.tthcc_driver_register_btn);
//        } else {
//            EventLogUtils.EventLog(self, EventLogUtils.tthcc_owner_register_btn);
//        }

        if (phoneNumEdit.getText() == null || regCodeEdit.getText() == null)
            return;

        String phonenum = phoneNumEdit.getText().toString();
        String regCode = regCodeEdit.getText().toString();


        if (phonenum.length() <= 0) {
            Utils.showToast(this, "请输入手机号码");
            return;
        }

        if (regCode.length() <= 0) {
            Utils.showToast(this, "请输入验证码");
            return;
        }
        if (phonenum.length() != 11) {
            //TODO 删掉手机号码，聚焦
            Utils.showToast(this, "手机号码格式错误，请重新输入");
            return;
        }

        if (regCode.length() != 6) {
            //TODO 删掉验证码，聚焦
            Utils.showToast(this, "手机号码格式错误，请重新输入");
            return;
        }


//        String versionType = Utils.getVersionType(this);
//        if(versionType.equals( "driver")){
//            Intent intent = new Intent(self, Bridge.shared().initActivityClass);
//            intent.putExtra("phonenum",phonenum);
//            startActivity(intent);
//        }else if(versionType.equals( "owner")) {
//            Intent intent = new Intent(self, Bridge.shared().initActivityClass);
//            intent.putExtra("phonenum",phonenum);
//            startActivity(intent);
//        }else {
//            Utils.showToast(this,"versonType error");
//            return;
//        }


    }

    private void initAppService(){
//        if (Utils.getSDKVersionNumber() > 7) {
//            //百度sdk inital
//            SDKInitializer.initialize(getApplicationContext());
//            //启动位置上报服务
//            startService(new Intent(this, BaiduMapService.class));
//        }
//        startService(new Intent(this, LocationService.class));
    }

    private void stopAppService(){
//        if (Utils.getSDKVersionNumber() > 7) {
//            //启动位置上报服务
//            stopService(new Intent(this, BaiduMapService.class));
//        }
//        stopService(new Intent(this, LocationService.class));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopAppService();
    }

}