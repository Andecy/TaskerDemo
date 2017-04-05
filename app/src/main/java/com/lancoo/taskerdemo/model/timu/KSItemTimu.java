package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.module.timu.ItemTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSItemTimu implements ItemTimu {
    private String optionAsk;

    public KSItemTimu(String optionAsk) {
        this.optionAsk = optionAsk;
    }

    @Override
    public float getScore() {
        return .8f;
    }

    @Override
    public String getOptionAsk() {
        return optionAsk;
    }

    @Override
    public int getIndex() {
        return 0;
    }
}
