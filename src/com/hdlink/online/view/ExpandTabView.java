package com.hdlink.online.view;

import java.util.ArrayList;

import android.view.ViewGroup;
import android.widget.*;
import com.hdlink.online.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow.OnDismissListener;

/**
 * 菜单控件头部，封装了下拉动画，动态生成头部按钮个数
 * 
 * @author yueyueniao
 */

public class ExpandTabView extends LinearLayout implements OnDismissListener {

	private ExpandTabButton selectedButton;
	private ArrayList<String> mTextArray = new ArrayList<String>();
	private ArrayList<RelativeLayout> mViewArray = new ArrayList<RelativeLayout>();
	private ArrayList<ExpandTabButton> mBtns = new ArrayList<ExpandTabButton>();
	private Context mContext;
	private final int SMALL = 0;
	private int displayWidth;
	private int displayHeight;
	private PopupWindow popupWindow;
	private int selectPosition;

	public ExpandTabView(Context context) {
		super(context);
		init(context);
	}

	public ExpandTabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	/**
	 * 根据选择的位置设置tabitem显示的值
	 */
	public void setTitle(String valueText, int position) {
		if (position < mBtns.size()) {
            View btn = mBtns.get(position);
            ((TextView)btn.findViewById(R.id.expandview_tab_text)).setText(valueText);
            TextView v;
		}
	}

	/**
	 * 根据选择的位置获取tabitem显示的值
	 */
	public String getTitle(int position) {
        View btn = mBtns.get(position);
        TextView btnText = ((TextView)btn.findViewById(R.id.expandview_tab_text));
		if (position < mBtns.size() && btnText.getText() != null) {
			return btnText.getText().toString();
		}
		return "";
	}

	/**
	 * 设置tabitem的个数和初始值
	 */
	public void setValue(ArrayList<String> textArray, ArrayList<View> viewArray) {
		if (mContext == null) {
			return;
		}
		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mTextArray = textArray;
		for (int i = 0; i < viewArray.size(); i++) {
			final RelativeLayout r = new RelativeLayout(mContext);
			int maxHeight = (int) (displayHeight * 0.7);
			RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, maxHeight);
//			rl.leftMargin = 10;
//			rl.rightMargin = 10;

			r.addView(viewArray.get(i), rl);
			mViewArray.add(r);
			r.setTag(SMALL);
//			ToggleButton tButton = (ToggleButton) inflater.inflate(R.layout.toggle_button, this, false);
            ExpandTabButton tButton = new ExpandTabButton(getContext());
            tButton.setFocusable(true);

            LinearLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
			addView(tButton, params);
			View line = new TextView(mContext);
			line.setBackgroundResource(R.drawable.choosebar_line);
			if (i < viewArray.size() - 1) {
				LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(2, LinearLayout.LayoutParams.FILL_PARENT);
				addView(line, lp);

			}
			mBtns.add(tButton);
			tButton.setTag(i);

            tButton.setText(mTextArray.get(i));

			r.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					onPressBack();
				}
			});

			r.setBackgroundColor(mContext.getResources().getColor(R.color.popup_main_background));

			tButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					// initPopupWindow();
//					ToggleButton tButton = (ToggleButton) view;
					ExpandTabButton tButton = (ExpandTabButton) view;

                    tButton.setChecked(true);
					if (selectedButton != null && selectedButton != tButton) {
						selectedButton.setChecked(false);
					}
					selectedButton = tButton;
					selectPosition = (Integer) selectedButton.getTag();
					startAnimation();
					if (mOnButtonClickListener != null && tButton.isChecked()) {
						mOnButtonClickListener.onClick(selectPosition);
					}

				}
			});
		}
	}

	private void startAnimation() {

		if (popupWindow == null) {
			popupWindow = new PopupWindow(mViewArray.get(selectPosition), displayWidth, displayHeight);
			popupWindow.setAnimationStyle(R.style.PopupWindowAnimation);
			popupWindow.setFocusable(true);
			popupWindow.setOutsideTouchable(true);
		}

		if (selectedButton.isChecked()) {
			if (!popupWindow.isShowing()) {
				showPopup(selectPosition);
			} else {
				popupWindow.setOnDismissListener(this);
				popupWindow.dismiss();
				hideView();
			}
		} else {
			if (popupWindow.isShowing()) {
				popupWindow.dismiss();
				hideView();
			}
		}
	}

	private void showPopup(int position) {
		View tView = mViewArray.get(selectPosition).getChildAt(0);
		if (tView instanceof ViewBaseAction) {
			ViewBaseAction f = (ViewBaseAction) tView;
			f.show();
		}
		if (popupWindow.getContentView() != mViewArray.get(position)) {
			popupWindow.setContentView(mViewArray.get(position));
		}
		popupWindow.showAsDropDown(this, 0, 0);
	}

	/**
	 * 如果菜单成展开状态，则让菜单收回去
	 */
	public boolean onPressBack() {
		if (popupWindow != null && popupWindow.isShowing()) {
			popupWindow.dismiss();
			hideView();
			if (selectedButton != null) {
				selectedButton.setChecked(false);
			}
			return true;
		} else {
			return false;
		}

	}

	private void hideView() {
		View tView = mViewArray.get(selectPosition).getChildAt(0);
		if (tView instanceof ViewBaseAction) {
			ViewBaseAction f = (ViewBaseAction) tView;
			f.hide();
		}
	}

	private void init(Context context) {
		mContext = context;
		displayWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
		displayHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
		setOrientation(LinearLayout.HORIZONTAL);
	}

    public void close(){
        popupWindow.dismiss();
        if(selectedButton != null && selectedButton.isChecked())
            selectedButton.setChecked(false);
    }

	@Override
	public void onDismiss() {
		showPopup(selectPosition);
		popupWindow.setOnDismissListener(null);
	}

	private OnButtonClickListener mOnButtonClickListener;

	/**
	 * 设置tabitem的点击监听事件
	 */
	public void setOnButtonClickListener(OnButtonClickListener l) {
		mOnButtonClickListener = l;
	}

	/**
	 * 自定义tabitem点击回调接口
	 */
	public interface OnButtonClickListener {
		public void onClick(int selectPosition);
	}

}
