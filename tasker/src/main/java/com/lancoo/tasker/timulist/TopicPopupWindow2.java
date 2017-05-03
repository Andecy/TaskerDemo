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
import com.lancoo.tasker.content.TaskData;


/**
 * Author: Andecy
 * Time: 2017/4/27
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicPopupWindow2 extends PopupWindow {

    private int mWidth;
    private int mHeight;

    private View mContentView;

    private TextView tv_title;
    private RecyclerView rv_content;

    private TaskData mTaskData;
    private int curTopicPosition;

    public TopicPopupWindow2(Context context, TaskData data, int curTopicPosition) {
        super(context);
        mTaskData = data;
        this.curTopicPosition = curTopicPosition;

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
        mContentView.findViewById(R.id.iv_list_number_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rv_content.setLayoutManager(new LinearLayoutManager(context));
        rv_content.setItemAnimator(new DefaultItemAnimator());

        rv_content.setAdapter(new TopicListAdapter(mTaskData.getTaskTimu().getTopicTimus(), mTaskData.getTaskAnswer().geTopicAnswers(), curTopicPosition));
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
