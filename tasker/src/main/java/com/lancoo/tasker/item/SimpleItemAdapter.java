package com.lancoo.tasker.item;

import android.support.v4.app.FragmentManager;

import com.lancoo.tasker.content.answer.IItemAnswer;
import com.lancoo.tasker.content.answer.ITaskAnswer;
import com.lancoo.tasker.content.timu.IItemTimu;
import com.lancoo.tasker.content.timu.ITaskTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class SimpleItemAdapter extends BaseItemAdapter {


    public SimpleItemAdapter(FragmentManager fm, ITaskTimu taskTimu, ITaskAnswer taskAnswer) {
        super(fm, taskTimu, taskAnswer);
    }

    @Override
    protected BaseItemFragment getItemByType(IItemTimu itemTimu, IItemAnswer itemAnswer) {
        return DefaultSingleChoiceFragment.newInstance(isAnswerable(), isStandardable(), itemTimu, itemAnswer);
//        switch ((int) (Math.random() * 10) % 2) {
//            case 0:
//                return DefaultFillBlankFragment.newInstance(isAnswerable(), isStandardable(), itemTimu, itemAnswer);
//            default:
//            case 1:
//                return DefaultSingleChoiceFragment.newInstance(isAnswerable(), isStandardable(), itemTimu, itemAnswer);
//        }
    }
}
