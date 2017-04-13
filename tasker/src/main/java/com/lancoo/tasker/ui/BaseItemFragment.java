package com.lancoo.tasker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.tool.UITool;
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
    private TextView tv_answer;
    private TextView tv_analysis;

    protected ItemTimu mItemTimu;
    protected ItemAnswer mItemAnswer;

    protected boolean mAnswerable;
    protected boolean mStandardable;

    @Override
    protected void findViews() {
        tv_que = findView(R.id.tv_item_topic_que);
        tv_answer = findView(R.id.tv_tasker_item_result);
        tv_analysis = findView(R.id.tv_tasker_item_analysis);
    }

    public void setItems(boolean answerable, boolean standardable, ItemTimu itemTimu, ItemAnswer itemAnswer) {
        mItemTimu = itemTimu;
        mItemAnswer = itemAnswer;
        mAnswerable = answerable;
        mStandardable = standardable;
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        UITool.setRichTitle(
                mItemTimu.getIndex() + "." +
                        (mItemTimu.getOptionAsk() == null ? "" : mItemTimu.getOptionAsk()) +
                        "(" + mItemTimu.getScore() + "分)", tv_que);

        initAnswerableView(mAnswerable);

        initStandardableView(mStandardable);

    }


    public abstract void initAnswerableView(boolean enabled);

    public void initStandardableView(boolean enabled) {
        if (tv_answer != null) {
            tv_answer.setText("标准答案：" + mItemTimu.getStandardAnswer());

            if (!TextUtils.isEmpty(mItemTimu.getStandardAnswer()) && enabled) {
                tv_answer.setVisibility(View.VISIBLE);
            } else {
                tv_answer.setVisibility(View.GONE);
            }
        }

        if (tv_analysis != null) {
            tv_analysis.setText("答案解析：" + mItemTimu.getAnalysis());

            if (!TextUtils.isEmpty(mItemTimu.getAnalysis()) && enabled) {
                tv_analysis.setVisibility(View.VISIBLE);
            } else {
                tv_analysis.setVisibility(View.GONE);
            }
        }

    }
}
