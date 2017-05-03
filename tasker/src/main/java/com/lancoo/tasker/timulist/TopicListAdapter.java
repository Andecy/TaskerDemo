package com.lancoo.tasker.timulist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lancoo.tasker.audio.SingleItemClickListener;
import com.lancoo.tasker.content.TaskData;

/**
 * Author: Andecy
 * Time: 2017/4/28
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicListAdapter extends RecyclerView.Adapter<TopicListVH> {
    private TaskData mTaskData;
    private int mCurTopicPosition;
    private SingleItemClickListener mItemClickListener;


    public TopicListAdapter(TaskData taskData, int curTopicPosition) {
        mTaskData = taskData;
        mCurTopicPosition = curTopicPosition;
    }

    @Override
    public TopicListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return TopicListVH.getInstance(parent, mCurTopicPosition, mItemClickListener);
    }

    @Override
    public void onBindViewHolder(TopicListVH holder, int position) {
        holder.handleData(mTaskData.getTaskTimu().getTopicTimus().get(position),
                mTaskData.getTaskAnswer().geTopicAnswers().get(position), position);
    }

    @Override
    public int getItemCount() {
        return mTaskData.getTaskTimu().getTopicTimus().size();
    }
}
