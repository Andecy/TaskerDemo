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

public abstract class BaseTimuListPopupWindow extends PopupWindow {

    private int mWidth;
    private int mHeight;

    private TextView tv_title;
    private RecyclerView rv_content;


    public BaseTimuListPopupWindow(Context context, int curPosition) {
        super(context);

        calWidthAndHeight(context);
        setWidth(mWidth);
        setHeight(mHeight);

        View view = LayoutInflater.from(context).inflate(R.layout.tasker_list_number, null);
        //设置布局与相关属性
        setContentView(view);
        setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        setFocusable(true);
        setTouchable(true);

        findView(view);
    }

    protected void setAdapter(RecyclerView.Adapter adapter){
        if (rv_content==null){
            return;
        }

        rv_content.setAdapter(adapter);
    }

    private void findView(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_list_number_title);
        rv_content = (RecyclerView) view.findViewById(R.id.rv_list_number_content);
        rv_content.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rv_content.setItemAnimator(new DefaultItemAnimator());
        view.findViewById(R.id.iv_list_number_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
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
