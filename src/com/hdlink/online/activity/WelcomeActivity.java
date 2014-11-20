package com.hdlink.online.activity;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Handler;
import com.hdlink.online.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.hdlink.online.net.NetProtocol;
import com.hdlink.online.net.NetService;

/**
 * 
 * @author tuwei
 *
 */
public class WelcomeActivity extends NoActionBarActivity {

	private static final long DELAYED_TIME = 3000; 
	private Intent intent;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

        // Set Runnable to remove splash screen just in case
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                self.startActivity(new Intent(self, MainActivity.class));
//                new NetService(self).quickLogin(new NetService.NetCallBack() {
//                    @Override
//                    public void onCall(NetProtocol result) {
//                        if(result.code == NetProtocol.SUCCESS){
//                            self.startActivity(new Intent(self, MainActivity.class));
//                        }else{
//                            self.startActivity(new Intent(self, LoginActivity.class));
//                        }
//                    }
//                });
            }
        }, 1000);
	}

}
