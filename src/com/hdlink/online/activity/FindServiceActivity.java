package com.hdlink.online.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.hdlink.online.R;
import com.hdlink.online.fragments.BaseFragment;
import com.hdlink.online.fragments.FindServiceListFragment;
import com.hdlink.online.model.CarService;
import com.hdlink.online.model.CarServiceCenter;
import com.hdlink.online.model.GlobalConfig;
import com.hdlink.online.view.ExpandTabView;
import com.hdlink.online.view.FilterTabPopView;

import java.util.*;

public class FindServiceActivity extends BackBaseActivity {

    private String mServiceId = "";
    private List<String> fragmentTags = Arrays.asList("findServiceListFrag", "findServiceMapFrag");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findservice);

        View actionbar = setActionBarLayout(R.layout.activity_findservice_actionbar);
        setLogoType(WITH_BACK);
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

        //一般是从homepagfragment中传来
        if(getIntent().hasExtra("serviceId")){

            mServiceId = getIntent().getStringExtra("serviceId");

            FindServiceListFragment listFragment = (FindServiceListFragment)getSupportFragmentManager().findFragmentById(R.id.findservice_list_frag);
            listFragment.setService(mServiceId);
        }

	}

    public void setCurrFrag(String tag){
        FragmentManager manager = getSupportFragmentManager();
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

}
