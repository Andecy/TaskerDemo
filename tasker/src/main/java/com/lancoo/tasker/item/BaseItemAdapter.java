package com.lancoo.tasker.item;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lancoo.tasker.content.TaskData;
import com.lancoo.tasker.content.answer.IItemAnswer;
import com.lancoo.tasker.content.timu.IItemTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: 描述不同体型采用的解析Fragment
 */

public abstract class BaseItemAdapter extends FragmentStatePagerAdapter {

    private boolean mAnswerable = true;
    private boolean mStandardable = true;

    private TaskData mData;

    private int curTopicPosition;

    public BaseItemAdapter(FragmentManager fm, TaskData data) {
        super(fm);
        mData = data;
    }

    public void update(int topicPosition) {
        curTopicPosition = topicPosition;
        notifyDataSetChanged();
    }

    public TaskData getData() {
        return mData;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public BaseItemFragment getItem(int position) {
        return getItemByType(
                mData.getTaskTimu().getTopicTimus().get(curTopicPosition).getItemTimus().get(position),
                mData.getTaskAnswer().geTopicAnswers().get(curTopicPosition).getItemAnswers().get(position));
    }

    protected abstract BaseItemFragment getItemByType(IItemTimu itemTimu, IItemAnswer itemAnswer);

    @Override
    public int getCount() {
        return mData.getTaskTimu().getTopicTimus().get(curTopicPosition).getItemTimus().size();
    }

    public boolean isAnswerable() {
        return mAnswerable;
    }

    public boolean isStandardable() {
        return mStandardable;
    }


    /**
     * 设置是否展示标准答案和解析
     * true==
     * @param answerable
     */
    public void setAnswerable(boolean answerable) {
        if (mAnswerable != answerable) {
            mAnswerable = answerable;
            notifyDataSetChanged();
        }

    }

    public void setAnalysisable(boolean standardable) {
        if (mStandardable != standardable) {
            mStandardable = standardable;
            notifyDataSetChanged();
        }

    }
}
