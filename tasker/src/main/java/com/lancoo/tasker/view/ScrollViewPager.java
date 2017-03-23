package com.lancoo.tasker.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 作者(Author): Andecy on 2016/3/14.
 * 邮箱(Email): andecy@foxmail.com
 * 描述(Description): 是否允许滑动的ScrollView
 */
public class ScrollViewPager extends ViewPager {
    private boolean isCanScroll = true;
    public void setCanScroll(boolean canScroll) {
        isCanScroll = canScroll;
    }
    public ScrollViewPager(Context context) {
        super(context);
    }

    public ScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }
}
