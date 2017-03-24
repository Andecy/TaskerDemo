package com.lancoo.tasker.adapter;

import android.support.v4.app.FragmentManager;

import com.lancoo.tasker.module.TaskData;
import com.lancoo.tasker.ui.BaseItemFragment;
import com.lancoo.tasker.ui.DefaultSingleChoiceFragment;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class SimpleItemAdapter extends BaseItemAdapter {
    private boolean mAnswerable = true;

    public SimpleItemAdapter(FragmentManager fm, TaskData data) {
        super(fm, data);
    }

    @Override
    protected BaseItemFragment getItemByType(TaskData data, int topicPosition, int itemPosition) {
        return DefaultSingleChoiceFragment.newInstance(true,
                data.getTaskTimu().getTopicTimus().get(topicPosition).getItemTimus().get(itemPosition),
                data.getTaskAnswer().geTopicAnswers().get(topicPosition).getItemAnswers().get(itemPosition));
    }
}
