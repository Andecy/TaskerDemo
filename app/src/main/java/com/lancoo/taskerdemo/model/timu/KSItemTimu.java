package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.module.timu.ItemTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSItemTimu implements ItemTimu {
    @Override
    public float getScore() {
        return .8f;
    }

    @Override
    public String getOptionAsk() {
        return "asfagsdfgfgsdfgsdgf";
    }

    @Override
    public int getIndex() {
        return 0;
    }
}
