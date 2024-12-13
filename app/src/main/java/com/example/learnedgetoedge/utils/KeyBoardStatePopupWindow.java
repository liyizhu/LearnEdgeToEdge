package com.example.learnedgetoedge.utils;

import android.app.Activity;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

/*
 * Created by liyizhu on 2022/10/10.
 */
public class KeyBoardStatePopupWindow extends PopupWindow implements ViewTreeObserver.OnGlobalLayoutListener {

	private final Activity mActivity;
	private final View rootView;
	private HeightListener listener;
	private final int totalHeight;

	private int lastHeight;

	public KeyBoardStatePopupWindow(Activity activity) {
		super(activity);
		this.mActivity = activity;

		// 基础配置
		rootView = new View(activity);
		setContentView(rootView);

		// 监听全局Layout变化
		rootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
		setBackgroundDrawable(new ColorDrawable(0));

		View contentView = activity.findViewById(android.R.id.content);
		totalHeight = contentView.getHeight();

		// 设置宽度为0，高度为全屏
		setWidth(0);
		setHeight(WindowManager.LayoutParams.MATCH_PARENT);

		// 设置键盘弹出方式
		setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
	}

	public KeyBoardStatePopupWindow init() {
		if (!isShowing()) {
			final View view = mActivity.getWindow().getDecorView();
			// 延迟加载popupwindow，如果不加延迟就会报错
			view.post(() -> showAtLocation(view, Gravity.NO_GRAVITY, 0, 0));
		}
		return this;
	}

	public KeyBoardStatePopupWindow setHeightListener(HeightListener listener) {
		this.listener = listener;
		return this;
	}

	@Override
	public void onGlobalLayout() {
		if (totalHeight <= 0) {
			return;
		}
		Rect rect = new Rect();
		rootView.getWindowVisibleDisplayFrame(rect);
		Log.e("TAG", "initKeyBoardHeightProvider: " + totalHeight + " " + rect.bottom);

		// 两者的差值就是键盘的高度
		int keyboardHeight = totalHeight - rect.bottom;
		if (lastHeight != keyboardHeight && listener != null) {
			lastHeight = keyboardHeight;
			listener.onHeightChanged(keyboardHeight);
		}
	}

	public interface HeightListener {
		void onHeightChanged(int height);
	}
}

