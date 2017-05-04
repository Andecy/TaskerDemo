package com.lancoo.tasker.item;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lancoo.tasker.content.answer.IItemAnswer;
import com.lancoo.tasker.content.answer.ITaskAnswer;
import com.lancoo.tasker.content.timu.IItemTimu;
import com.lancoo.tasker.content.timu.ITaskTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: 描述不同体型采用的解析Fragment
 */

public abstract class BaseItemAdapter<T extends ITaskTimu, A extends ITaskAnswer> extends FragmentStatePagerAdapter {

    private boolean mAnswerable = true;
    private boolean mStandardable = true;

    private T mTaskTimu;
    private A mTaskAnswer;

    private int curTopicPosition;


    public BaseItemAdapter(FragmentManager fm, T taskTimu, A taskAnswer) {
        super(fm);
        mTaskTimu = taskTimu;
        mTaskAnswer = taskAnswer;
    }

    public void update(int topicPosition) {
        curTopicPosition = topicPosition;
        notifyDataSetChanged();
    }

    public T getTaskTimu() {
        return mTaskTimu;
    }

    public A getTaskAnswer() {
        return mTaskAnswer;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public BaseItemFragment getItem(int position) {
        return getItemByType(
                mTaskTimu.getTopicTimus().get(curTopicPosition).getItemTimus().get(position),
                mTaskAnswer.geTopicAnswers().get(curTopicPosition).getItemAnswers().get(position));
    }

    protected abstract BaseItemFragment getItemByType(IItemTimu itemTimu, IItemAnswer itemAnswer);

    @Override
    public int getCount() {
        return mTaskTimu.getTopicTimus().get(curTopicPosition).getItemTimus().size();
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
     *
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
