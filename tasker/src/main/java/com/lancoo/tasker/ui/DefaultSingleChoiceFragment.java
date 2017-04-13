package com.lancoo.tasker.ui;

import android.support.annotation.IdRes;
import android.view.LayoutInflater;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lancoo.tasker.R;
import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.timu.ItemTimu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DefaultSingleChoiceFragment extends BaseItemFragment implements RadioGroup.OnCheckedChangeListener {

    private RadioGroup mRadioGroup;

    private List<RadioButton> mRadioButtons;

    public static DefaultSingleChoiceFragment newInstance(boolean answerable, boolean standardable, ItemTimu itemTimu, ItemAnswer itemAnswer) {
        DefaultSingleChoiceFragment fragment = new DefaultSingleChoiceFragment();
        fragment.setItems(answerable, standardable, itemTimu, itemAnswer);
        return fragment;
    }


    @Override
    protected void findViews() {
        super.findViews();
        mRadioGroup = findView(R.id.rg_tasker_item_answer);
        mRadioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void initAnswerableView(boolean b) {
        if (mRadioButtons == null) {
            mRadioButtons = new ArrayList<>();
            for (int i = 0; i < Math.min(mItemTimu.getOptions().length, mItemTimu.getOptionsKey().length); i++) {
                RadioButton radioButton = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.tasker_module_item_topic_radiobtn, null);
                radioButton.setText(mItemTimu.getOptionsKey()[i] + "." + mItemTimu.getOptions()[i]);
                radioButton.setChecked(mItemTimu.getOptionsKey()[i].equals(mItemAnswer.getAnswer()));
                radioButton.setTag(mItemTimu.getOptionsKey()[i]);
                radioButton.setId(i);
                mRadioGroup.addView(radioButton);
                mRadioButtons.add(radioButton);
                radioButton.setEnabled(b);
                radioButton.setSaveEnabled(false);
            }
        } else {
            for (RadioButton radioButton : mRadioButtons) {
                radioButton.setEnabled(b);
            }
        }

    }

    @Override
    public void initStandardableView(boolean b) {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.tasker_fragment_item_single_choice;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        RadioButton rb = (RadioButton) group.findViewById(checkedId);
        if (rb != null && rb.getTag() != null) {
            mItemAnswer.setAnswer(String.valueOf(rb.getTag()));
        }
    }
}
