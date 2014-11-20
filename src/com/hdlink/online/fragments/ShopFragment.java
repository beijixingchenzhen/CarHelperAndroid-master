package com.hdlink.online.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.hdlink.online.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.hdlink.online.activity.BaseActivity;

import java.util.Arrays;
import java.util.List;


/**
 * 
 * @author tuwei
 * 
 * 
 */

public class ShopFragment extends BaseFragment {

	private Button back_btn;
	private TextView map_txvTitle;
	private View view;

    private List<String> fragmentTags = Arrays.asList("findShopList", "findShopMap");

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.tab_shop, null);
		init();

        View actionbar = getBaseActivity().setActionBarLayout(R.layout.activity_findservice_actionbar);
        final Button listBtn = (Button)actionbar.findViewById(R.id.findservice_headbar_list);
        final Button mapBtn = (Button)actionbar.findViewById(R.id.findservice_headbar_map);


        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBtn.setBackgroundColor(getResources().getColor(R.color.grey));
                mapBtn.setBackgroundColor(getResources().getColor(R.color.white));

                setCurrFrag(fragmentTags.get(0));
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listBtn.setBackgroundColor(getResources().getColor(R.color.white));
                mapBtn.setBackgroundColor(getResources().getColor(R.color.grey));

                setCurrFrag(fragmentTags.get(1));
            }
        });


		return view;
	}

    public void setCurrFrag(String tag){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        for(String t : fragmentTags){
            Fragment frag = (Fragment)manager.findFragmentByTag(t);
            if(t.equals(tag)){
                transaction.show(frag);
            }else{
                transaction.hide(frag);
            }
        }

        transaction.setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out);
        transaction.commit();
    }

    @Override
    public void onShow() {
        getBaseActivity().setDefaultLayout("商店", BaseActivity.WITH_NONE);
    }

	private void init() {

	}


}
