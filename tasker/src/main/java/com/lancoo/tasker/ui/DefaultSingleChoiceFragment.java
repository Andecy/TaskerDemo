package com.lancoo.tasker.ui;

import com.lancoo.tasker.R;
import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.timu.ItemTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DefaultSingleChoiceFragment extends BaseItemFragment {


    public static DefaultSingleChoiceFragment newInstance(boolean answerable, ItemTimu itemTimu, ItemAnswer itemAnswer) {
        DefaultSingleChoiceFragment fragment = new DefaultSingleChoiceFragment();
        fragment.setItems(answerable, itemTimu, itemAnswer);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tasker_item_single_choice;
    }
}
