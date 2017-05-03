package com.lancoo.tasker.item;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.lancoo.tasker.R;
import com.lancoo.tasker.content.answer.ItemAnswer;
import com.lancoo.tasker.content.timu.ItemTimu;
import com.lancoo.tasker.view.WritableEditText;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DefaultFillBlankFragment extends BaseItemFragment implements TextWatcher {

    private WritableEditText et_answer;

    public static DefaultFillBlankFragment newInstance(boolean answerable, boolean standardable, ItemTimu itemTimu, ItemAnswer itemAnswer) {
        DefaultFillBlankFragment fragment = new DefaultFillBlankFragment();
        fragment.setItems(answerable, standardable, itemTimu, itemAnswer);
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
        et_answer.setText(mItemAnswer.getAnswer());
        et_answer.addTextChangedListener(this);
    }

    @Override
    public void initAnswerableView(boolean b) {
        et_answer.setEditable(getActivity(), b);
    }


    @Override
    protected int getContentViewId() {
        return R.layout.tasker_fragment_item_fill_blank;
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
