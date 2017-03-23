package com.lancoo.tasker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lancoo.tasker.R;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DefaultSingleChoiceFragment extends BaseItemFragment {


    public static DefaultSingleChoiceFragment newInstance() {
        Bundle args = new Bundle();
        DefaultSingleChoiceFragment fragment = new DefaultSingleChoiceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tasker_item_single_choice;
    }
}
