package com.lancoo.tasker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.UITool;
import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.timu.ItemTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public abstract class BaseItemFragment extends TBaseFragment {

    protected TextView tv_que;

    protected ItemTimu mItemTimu;
    protected ItemAnswer mItemAnswer;

    protected boolean mAnswerable;

    @Override
    protected void findViews() {
        tv_que = findView(R.id.tv_item_topic_que);
    }

    public void setItems(boolean answerable, ItemTimu itemTimu, ItemAnswer itemAnswer) {
        Bundle args = new Bundle();
        args.putSerializable("timu", itemTimu);
        args.putSerializable("answer", itemAnswer);
        args.putBoolean("answerable", answerable);
        setArguments(args);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        mItemAnswer = (ItemAnswer) getArguments().getSerializable("answer");
        mItemTimu = (ItemTimu) getArguments().getSerializable("timu");
        mAnswerable = getArguments().getBoolean("answerable");

        UITool.setRichTitle(
                mItemTimu.getIndex() + "." +
                        (mItemTimu.getOptionAsk() == null ? "" : mItemTimu.getOptionAsk()) +
                        "(" + mItemTimu.getScore() + "分)", tv_que);
    }
}
