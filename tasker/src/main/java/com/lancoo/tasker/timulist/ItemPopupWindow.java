package com.lancoo.tasker.timulist;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.lancoo.tasker.adapter.SingleItemClickListener;
import com.lancoo.tasker.content.answer.IItemAnswer;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/27
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class ItemPopupWindow extends BaseTimuListPopupWindow {

    public ItemPopupWindow(Context context, int curItemPosition, List<? extends IItemAnswer> itemAnswers, SingleItemClickListener listener) {
        super(context, listener);
        setAdapter(new ItemListAdapter(itemAnswers, curItemPosition),
                new GridLayoutManager(context, 4));
    }
}
