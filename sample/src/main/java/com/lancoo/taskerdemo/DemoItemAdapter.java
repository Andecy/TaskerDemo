package com.lancoo.taskerdemo;

import android.support.v4.app.FragmentManager;

import com.lancoo.tasker.content.answer.IItemAnswer;
import com.lancoo.tasker.content.answer.ITaskAnswer;
import com.lancoo.tasker.content.timu.IItemTimu;
import com.lancoo.tasker.content.timu.ITaskTimu;
import com.lancoo.tasker.item.BaseItemAdapter;
import com.lancoo.tasker.item.BaseItemFragment;
import com.lancoo.tasker.item.DefaultFillBlankFragment;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DemoItemAdapter extends BaseItemAdapter {


    public DemoItemAdapter(FragmentManager fm, ITaskTimu taskTimu, ITaskAnswer taskAnswer) {
        super(fm, taskTimu, taskAnswer);
    }

    @Override
    protected BaseItemFragment getItemByType(IItemTimu itemTimu, IItemAnswer itemAnswer) {
        return DefaultFillBlankFragment.newInstance(isAnswerable(), isStandardable(), itemTimu, itemAnswer);
//        return DefaultSingleChoiceFragment.newInstance(isAnswerable(), isStandardable(), itemTimu, itemAnswer);
//        switch ((int) (Math.random() * 10) % 2) {
//            case 0:
//                return DefaultFillBlankFragment.newInstance(isAnswerable(), isStandardable(), itemTimu, itemAnswer);
//            default:
//            case 1:
//                return DefaultSingleChoiceFragment.newInstance(isAnswerable(), isStandardable(), itemTimu, itemAnswer);
//        }
    }
}
