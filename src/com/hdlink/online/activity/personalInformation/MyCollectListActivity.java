package com.hdlink.online.activity.personalInformation;

import com.hdlink.online.R;
import com.hdlink.online.activity.BaseActivity;
import com.hdlink.online.activity.personalInformation.Fragment.AllOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForConsumptionOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.ForEvaluationOrderListFragment;
import com.hdlink.online.activity.personalInformation.Fragment.MyCollectServiceFragment;
import com.hdlink.online.activity.personalInformation.Fragment.MyCollectShopFragment;
import com.hdlink.online.activity.personalInformation.Fragment.NoPaymentOrderListFragment;
import com.hdlink.online.fragments.BaseFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author tuwei
 * 
 *         更多分类
 * 
 */

public class MyCollectListActivity extends BaseActivity {

	private Button back_btn, right_btn;
	private TextView map_txvTitle;
	private FragmentTabHost mTabHost;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_collect_list);
		 initTabs();

	}

	private void initTabs() {

		TabHost tabhost = (TabHost) findViewById(R.id.collect_tabhost);
		tabhost.setup();
		tabhost.addTab(tabhost.newTabSpec(getResources().getString(R.string.main_homepage_tab)).setIndicator(createTab("商户")).setContent(R.id.collect_service));
		
		tabhost.addTab(tabhost.newTabSpec(getResources().getString(R.string.main_shop_tab)).setIndicator(createTab("服务")).setContent(R.id.collect_shop));

		tabhost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
				setCurrTab(tabId);
			}
		});

		setCurrTab(getResources().getString(R.string.main_homepage_tab));
	}

	private View createTab(String name) {
		View v = LayoutInflater.from(self).inflate(R.layout.main_tab, null);
		((TextView) v.findViewById(R.id.main_tab_text)).setText(name);
		return v;
	}

	public void setCurrTab(String tabId) {
		FragmentManager manager = self.getSupportFragmentManager();
		Fragment curr = manager.findFragmentByTag(tabId);
		if (curr instanceof BaseFragment) {
			((BaseFragment) curr).onShow();
		}
	}

}
