package com.hdlink.online.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.hdlink.online.R;

import java.util.zip.Inflater;

/**
 * Created by zhongqiling on 14-11-14.
 */
public class ExpandTabButton extends LinearLayout {

    private boolean mIsChecked = false;

    public ExpandTabButton(Context context){
        super(context);

        LayoutInflater.from(context).inflate(R.layout.expandview_tab, this);
        setFocusable(true);
    }

    public void setChecked(boolean value){
        mIsChecked = value;
        int colorRes = value ? R.color.main_orange : R.color.black;
        TextView text = (TextView)findViewById(R.id.expandview_tab_text);
        if(getResources() != null){
            ColorStateList color = getResources().getColorStateList(colorRes);
            text.setTextColor(color);
        }
    }

    public boolean isChecked(){
        return mIsChecked;
    }

    public void setText(String text){
        ((TextView)findViewById(R.id.expandview_tab_text)).setText(text);
    }


}
