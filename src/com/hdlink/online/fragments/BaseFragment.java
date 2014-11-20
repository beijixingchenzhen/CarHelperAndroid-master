package com.hdlink.online.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import com.hdlink.online.activity.BaseActivity;

/**
 * Created by teddywu on 14-6-19.
 */
public class BaseFragment extends Fragment {

    protected BaseFragment self = this;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

    public BaseActivity getBaseActivity(){
        return (BaseActivity)getActivity();
    }

    public void onShow() {
    }

}


