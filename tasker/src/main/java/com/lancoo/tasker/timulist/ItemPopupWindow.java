package com.lancoo.tasker.timulist;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.lancoo.tasker.adapter.SingleItemClickListener;
import com.lancoo.tasker.content.answer.ItemAnswer;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/27
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class ItemPopupWindow extends BaseTimuListPopupWindow {

    public ItemPopupWindow(Context context, int curItemPosition, List<ItemAnswer> itemAnswers, SingleItemClickListener listener) {
        super(context, curItemPosition, listener);
        setAdapter(new ItemListAdapter(itemAnswers, curItemPosition),
                new GridLayoutManager(context, 4));
    }
}
