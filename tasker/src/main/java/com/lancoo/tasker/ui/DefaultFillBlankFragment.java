package com.lancoo.tasker.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.lancoo.tasker.R;
import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.timu.ItemTimu;
import com.lancoo.tasker.view.WritableEditText;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DefaultFillBlankFragment extends BaseItemFragment implements TextWatcher {

    private WritableEditText et_answer;

    public static DefaultFillBlankFragment newInstance(boolean answerable, ItemTimu itemTimu, ItemAnswer itemAnswer) {
        Log.w("Fill", "newInstance-->" + itemAnswer.getAnswer());

        DefaultFillBlankFragment fragment = new DefaultFillBlankFragment();
        fragment.setItems(answerable, itemTimu, itemAnswer);
        return fragment;
    }

    @Override
    protected void findViews() {
        super.findViews();
        et_answer = findView(R.id.et_tasker_item_answer);
    }

    @Override
    protected void initView(View view, @Nullable Bundle savedInstanceState) {
        super.initView(view, savedInstanceState);
        Log.w("Fill", "initView-->" + mItemAnswer.getAnswer());
        et_answer.setText("123");
        et_answer.setSaveEnabled(false);
//        et_answer.addTextChangedListener(this);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tasker_item_fill_blank;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mItemAnswer.setAnswer(String.valueOf(s));
    }
}
