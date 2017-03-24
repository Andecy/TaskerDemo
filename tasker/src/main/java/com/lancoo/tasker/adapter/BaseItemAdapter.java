package com.lancoo.tasker.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

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

    private TaskData mData;

    private int curTopicPosition;

    public BaseItemAdapter(FragmentManager fm, TaskData data) {
        super(fm);
        mData = data;
    }

    public TaskData getData() {
        return mData;
    }

    @Override
    public Fragment getItem(int position) {
        return getItemByType(
                mData.getTaskTimu().getTopicTimus().get(curTopicPosition).getItemTimus().get(position),
                mData.getTaskAnswer().geTopicAnswers().get(curTopicPosition).getItemAnswers().get(position));
    }

    protected abstract BaseItemFragment getItemByType(ItemTimu itemTimu, ItemAnswer itemAnswer);

    @Override
    public int getCount() {
        return mData.getTaskTimu().getTopicTimus().get(curTopicPosition).getItemTimus().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "" + (position + 1);
    }


}
