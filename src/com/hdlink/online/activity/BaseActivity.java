package com.hdlink.online.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hdlink.online.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teddywu on 14-6-19.
 */
public class BaseActivity extends ActionBarActivity {

    public static BaseActivity currActivity = null;
    private static List<BaseActivity> activities = new ArrayList<BaseActivity>();
    public static List<BaseActivity> getActivities(){ return activities; }

    public final static int WITH_BACK = 1;
    public final static int WITH_MENU = 2;
    public final static int WITH_NONE = 0;
    public final static int NONE = -1;

    public final BaseActivity self = this;

    public boolean hasStop = false;

    private View actionbarView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activities.add(this);
        initActionBar();
    }
    @Override
    protected void onResume() {
        super.onResume();
        currActivity = this;
    }
    @Override
    protected void onStart() {
        super.onStart();
        hasStop = false;
    }

    @Override
    protected void onPause() {
        super.onPause();
        clearReference();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearReference();
        activities.remove(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        hasStop = true;
    }

    private void clearReference() {
        if (currActivity != null && currActivity == self) {
            currActivity = null;
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.empty, menu); //为了占位
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
    }

    public void setTitle(String title) {

        Context context = getApplicationContext();
        if(context == null)
            return;

        ViewGroup content = (ViewGroup)actionbarView.findViewById(R.id.actionbar_content);
        TextView textView = (TextView)content.findViewById(430074);
        if(textView == null){
            content.removeAllViews();

            textView = new TextView(context);
            textView.setTextColor(getResources().getColorStateList(R.color.black));
//            textView.setTextSize(R.dimen.action_bar_text_size);
            textView.setGravity(Gravity.CENTER);
            textView.setId(430074);
            content.addView(textView, getActionBarLayoutParms());
        }
        textView.setText(title);
    }

    private LinearLayout.LayoutParams getActionBarLayoutParms(){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        return params;
    }

    public void initActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
//            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
//            actionBar.setDisplayShowTitleEnabled(false);

            LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            actionbarView = inflator.inflate(R.layout.actionbar_custom, null);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            getSupportActionBar().setCustomView(actionbarView, layout);
        }
    }

    public void setLogoType(final int type){

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            LinearLayout logoView = (LinearLayout)actionbarView.findViewById(R.id.actionbar_logo);
            View logo = null;
            if (type == WITH_BACK) {
                ImageView img = new ImageView(getApplicationContext());
//                img.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                img.setImageResource(R.drawable.back);
                logo = img;
            }
            if(logo != null){
                logoView.removeAllViews();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;
                logoView.addView(logo, params);
                logoView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onLogoClick(type);
                    }
                });
            }

        }
    }

    public void onLogoClick(int type){
        if(type == WITH_BACK){
            finish();
        }
    }

    public void setDefaultLayout(String title, int type){
        setTitle(title);
        setLogoType(type);
    }

    public View setActionBarLayout(int layoutRes){
        ViewGroup content = (ViewGroup)actionbarView.findViewById(R.id.actionbar_content);
        content.removeAllViews();

        LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflator.inflate(layoutRes, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content.addView(v, params);
        return v;
    }


}
