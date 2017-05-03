package com.lancoo.tasker.adapter;

import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lancoo.tasker.audio.SingleItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/28
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    private SingleItemClickListener mItemClickListener;
    private List<T> mDataList;
    private int currentPos;
    private BaseRecyclerViewHolder<T> mViewHolder;

    public BaseRecyclerViewAdapter(@Nullable List<T> dataList) {
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        mDataList = dataList;
    }

    public void setItemClickListener(SingleItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public SingleItemClickListener getItemClickListener() {
        return mItemClickListener;
    }


    public int getItemCount() {
        return this.mDataList == null ? 0 : this.mDataList.size();
    }

    public void setData(@NonNull List<T> data) {
        this.mDataList = data;
    }

    @Keep
    @NonNull
    public abstract BaseRecylerItem<T> createItem();


    public List<T> getData() {
        return this.mDataList;
    }

    public int getCurrentPosition() {
        return currentPos;
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mViewHolder = new BaseRecyclerViewHolder<>(parent, createItem(), mItemClickListener);
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        currentPos = position;
        holder.handleData(mDataList.get(position), position);
    }
}
