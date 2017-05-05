package com.lancoo.tasker.tool;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;

import com.blankj.utilcode.util.SizeUtils;
import com.lancoo.tasker.R;

/**
 * Author: Andecy
 * Time: 2017/4/17
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class LocalUtils {

    public static StateListDrawable generateChoiceOptionDrawble(Context context, String optionCode) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        TextDrawablePlus checkedDrawble = TextDrawablePlus.builder().beginConfig().width(SizeUtils.dp2px(36)).height(SizeUtils.dp2px(36)).textColor(Color.WHITE).endConfig().buildRound(optionCode, context.getResources().getColor(R.color.tasker_green_bg4));
        TextDrawablePlus normalDrawable = TextDrawablePlus.builder().beginConfig().width(SizeUtils.dp2px(36)).height(SizeUtils.dp2px(36)).borderColor(Color.BLACK).withBorder(2).textColor(context.getResources().getColor(R.color.tasker_txt_black3)).endConfig().buildRound(optionCode, Color.WHITE);
        stateListDrawable.addState(new int[]{android.R.attr.state_checked}, checkedDrawble);
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }
}
