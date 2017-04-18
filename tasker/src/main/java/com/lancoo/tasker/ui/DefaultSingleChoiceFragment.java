package com.lancoo.tasker.ui;

import android.os.Build;
import android.support.annotation.IdRes;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.utils.SizeUtils;
import com.lancoo.tasker.R;
import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.timu.ItemTimu;
import com.lancoo.tasker.tool.LocalUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
//                RadioButton radioButton = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.tasker_module_item_topic_radiobtn, null);

                RadioButton radioButton = new RadioButton(getActivity());
                radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                radioButton.setSaveEnabled(false);
                radioButton.setText(mItemTimu.getOptions()[i]);
                radioButton.setChecked(mItemTimu.getOptionsKey()[i].equals(mItemAnswer.getAnswer()));
                radioButton.setTag(mItemTimu.getOptionsKey()[i]);
                radioButton.setId(i);
                mRadioGroup.addView(radioButton);
                mRadioButtons.add(radioButton);
                radioButton.setEnabled(b);

                Pattern optionCodePattern = Pattern.compile("^[A-Z]");
                Matcher matcher = optionCodePattern.matcher(mItemTimu.getOptionsKey()[i]);

                while (matcher.find()) {
                    radioButton.setButtonDrawable(LocalUtils.generateChoiceOptionDrawble(getActivity(), matcher.group().replace("、", "")));
                }

                int leftPaddingPx = Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN ? SizeUtils.dp2px(6) : SizeUtils.dp2px(40);
                radioButton.setPadding(leftPaddingPx, SizeUtils.dp2px(18), SizeUtils.dp2px(6), SizeUtils.dp2px(18));
                radioButton.setTextColor(getResources().getColorStateList(R.color.pcs_exercise_option_color));
                radioButton.setTextSize(15);

            }
        } else {
            for (RadioButton radioButton : mRadioButtons) {
                radioButton.setEnabled(b);
            }
        }

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
