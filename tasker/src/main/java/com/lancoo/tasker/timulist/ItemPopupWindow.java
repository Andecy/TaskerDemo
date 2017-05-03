package com.lancoo.tasker.timulist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.lancoo.tasker.R;

/**
 * Author: Andecy
 * Time: 2017/4/27
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class ItemPopupWindow extends PopupWindow {

    private int mWidth;
    private int mHeight;

    private View mContentView;

    public ItemPopupWindow(Context context) {
        super(context);

        calWidthAndHeight(context);
        setWidth(mWidth);
        setHeight(mHeight);

        mContentView = LayoutInflater.from(context).inflate(R.layout.tasker_number_list_item, null);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //设置布局与相关属性
        setContentView(mContentView);
        setFocusable(true);
        setTouchable(true);
    }


    /**
     * 设置PopupWindow的大小
     *
     * @param context
     */
    private void calWidthAndHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);

        mWidth = (int) (metrics.widthPixels * 1);
        //设置高度为全屏高度的70%
        mHeight = (int) (metrics.heightPixels * 1);
    }
}
