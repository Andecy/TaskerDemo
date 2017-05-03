package com.lancoo.tasker.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Author: Andecy
 * Time: 2017/5/3
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface BaseRecylerItem<T> {
    @LayoutRes
    int getLayoutResId();

    void bindViews(View view);

    void setViews();

    void handleData(T data, int position);
}
