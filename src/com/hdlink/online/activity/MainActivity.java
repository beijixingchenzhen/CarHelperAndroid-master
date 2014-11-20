package com.hdlink.online.activity;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.hdlink.online.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import com.hdlink.online.fragments.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hdlink.online.R;
import com.hdlink.online.defines.ServiceFilterType;
import com.hdlink.online.fragments.HomePageFragment;
import com.hdlink.online.fragments.MyProfileFragment;
import com.hdlink.online.fragments.QAFragment;
import com.hdlink.online.fragments.ShopFragment;
import com.hdlink.online.model.CarService;
import com.hdlink.online.model.CarServiceCenter;

/**
 * 
 * @author tuwei
 *
 */

public class MainActivity extends BaseActivity{

	public static MainActivity INSTANCE;
	private FragmentTabHost mTabHost;
	private long exitTime = 0;

	@Override
	public void onCreate(Bundle arg0) {

		super.onCreate(arg0);

		setContentView(R.layout.activity_main);
        initTabs();

	}

    private void initTabs(){

        TabHost tabhost = (TabHost)findViewById(R.id.main_tabhost);
        tabhost.setup();
        tabhost.addTab(tabhost.newTabSpec(getResources().getString(R.string.main_homepage_tab)).setIndicator(createTab("首页")).setContent(R.id.main_homepage));
        tabhost.addTab(tabhost.newTabSpec(getResources().getString(R.string.main_shop_tab)).setIndicator(createTab("商店")).setContent(R.id.main_shop));
        tabhost.addTab(tabhost.newTabSpec(getResources().getString(R.string.main_qa_tab)).setIndicator(createTab("问答")).setContent(R.id.main_qa));
        tabhost.addTab(tabhost.newTabSpec(getResources().getString(R.string.main_profile_tab)).setIndicator(createTab("我的")).setContent(R.id.main_myprofile));

        tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                setCurrTab(tabId);
            }
        });

        setCurrTab(getResources().getString(R.string.main_homepage_tab));
    }

    private View createTab(String name){
        View v = LayoutInflater.from(self).inflate(R.layout.main_tab, null);
        ((TextView)v.findViewById(R.id.main_tab_text)).setText(name);
        return v;
    }

    public void setCurrTab(String tabId){
        FragmentManager manager = self.getSupportFragmentManager();
        Fragment curr = manager.findFragmentByTag(tabId);
        if (curr instanceof BaseFragment){
            ((BaseFragment) curr).onShow();
        }
    }


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {

			if ((System.currentTimeMillis() - exitTime) > 2000) {

				Toast.makeText(getApplicationContext(), "再按一次退出",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();

			} else {
				finish();
				System.exit(0);
			}

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}
