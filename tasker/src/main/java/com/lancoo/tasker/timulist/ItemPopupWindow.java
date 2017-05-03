package com.lancoo.tasker.timulist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

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

    private TextView tv_title;
    private RecyclerView rv_content;

    private int curItemPosition;

    public ItemPopupWindow(Context context, int curItemPosition) {
        super(context);
        this.curItemPosition = curItemPosition;

        calWidthAndHeight(context);
        setWidth(mWidth);
        setHeight(mHeight);

        mContentView = LayoutInflater.from(context).inflate(R.layout.tasker_list_number, null);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        //设置布局与相关属性
        setContentView(mContentView);
        setFocusable(true);
        setTouchable(true);

        tv_title = (TextView) mContentView.findViewById(R.id.tv_list_number_title);
        rv_content = (RecyclerView) mContentView.findViewById(R.id.rv_list_number_content);

        rv_content.setLayoutManager(new LinearLayoutManager(context));
        rv_content.setItemAnimator(new DefaultItemAnimator());

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
        //设置高度为全屏高度的100%
        mHeight = (int) (metrics.heightPixels * 1);
    }
}
