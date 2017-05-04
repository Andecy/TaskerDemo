package com.lancoo.tasker.timulist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lancoo.tasker.adapter.BaseRecyclerViewAdapter;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.IItemAnswer;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/28
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class ItemListAdapter extends BaseRecyclerViewAdapter<IItemAnswer> {
    private int curTopicPosition;

    public ItemListAdapter(@Nullable List<? extends IItemAnswer> dataList, int curTopicPosition) {
        super(dataList);
        this.curTopicPosition = curTopicPosition;
    }

    @NonNull
    @Override
    public BaseRecylerItem<IItemAnswer> createItem() {
        return new ItemListItem(curTopicPosition);
    }
}
