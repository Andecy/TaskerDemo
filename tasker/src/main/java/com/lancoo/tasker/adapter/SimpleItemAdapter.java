package com.lancoo.tasker.adapter;

import android.support.v4.app.FragmentManager;

import com.lancoo.tasker.module.TaskData;
import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.timu.ItemTimu;
import com.lancoo.tasker.ui.BaseItemFragment;
import com.lancoo.tasker.ui.DefaultFillBlankFragment;
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
    protected BaseItemFragment getItemByType(ItemTimu itemTimu, ItemAnswer itemAnswer) {

        switch ((int) (Math.random() * 10) % 2) {
            case 0:
                return DefaultFillBlankFragment.newInstance(true, itemTimu, itemAnswer);
            default:
            case 1:
                return DefaultSingleChoiceFragment.newInstance(true, itemTimu, itemAnswer);
        }
    }
}
