package com.lancoo.tasker.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.lancoo.tasker.module.TaskData;
import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.timu.ItemTimu;
import com.lancoo.tasker.ui.BaseItemFragment;

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

    private BaseItemFragment curFragment;

    public BaseItemAdapter(FragmentManager fm, TaskData data) {
        super(fm);
        mData = data;
    }

    public void update(int topicPosition) {
        curTopicPosition = topicPosition;
        notifyDataSetChanged();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        curFragment = (BaseItemFragment) object;
        super.setPrimaryItem(container, position, object);
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

    protected abstract BaseItemFragment getItemByType(ItemTimu itemTimu, ItemAnswer itemAnswer);

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

    public void setAnswerable(boolean answerable) {
        if (mAnswerable != answerable && curFragment != null) {
            curFragment.initAnswerableView(answerable);
        }
        mAnswerable = answerable;
    }

    public void setStandardable(boolean standardable) {
        if (mStandardable != standardable && curFragment != null) {
            curFragment.initStandardableView(standardable);
        }
        mStandardable = standardable;

    }
}
