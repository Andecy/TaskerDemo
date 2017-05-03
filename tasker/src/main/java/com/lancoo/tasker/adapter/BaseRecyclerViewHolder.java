package com.lancoo.tasker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Andecy
 * Time: 2017/4/28
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class BaseRecyclerViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    private SingleItemClickListener mItemClickListener;
    private int curPosition;
    protected BaseRecylerItem<T> mItem;

    public BaseRecyclerViewHolder(ViewGroup parent, BaseRecylerItem item, SingleItemClickListener singleItemClickListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(item.getLayoutResId(), parent, false));
        mItemClickListener = singleItemClickListener;
        itemView.setOnClickListener(this);
        mItem = item;
        mItem.bindViews(itemView);
        mItem.setViews();
    }

    public void handleData(T data, int position) {
        curPosition = position;
        mItem.handleData(data, position);
    }


    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemOnClick(v, curPosition);
        }
    }
}
