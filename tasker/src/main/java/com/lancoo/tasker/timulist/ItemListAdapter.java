package com.lancoo.tasker.timulist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lancoo.tasker.adapter.BaseRecyclerViewAdapter;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.ItemAnswer;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/28
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class ItemListAdapter extends BaseRecyclerViewAdapter<ItemAnswer> {
    private int curTopicPosition;

    public ItemListAdapter(@Nullable List<ItemAnswer> dataList, int curTopicPosition) {
        super(dataList);
        this.curTopicPosition = curTopicPosition;
    }

    @NonNull
    @Override
    public BaseRecylerItem<ItemAnswer> createItem() {
        return new ItemListItem(curTopicPosition);
    }
}
