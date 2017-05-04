package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.content.timu.IItemTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSItemTimu implements IItemTimu {
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

    @Override
    public String[] getOptions() {
        return new String[]{"aaaaa", "bbbbb", "ccccc", "ddddd"};
    }

    @Override
    public String[] getOptionsKey() {
        return new String[]{"A", "B", "C", "D"};
    }

    @Override
    public String getStandardAnswer() {
        return "我是标准答案";
    }

    @Override
    public String getAnalysis() {
        return "我是答案解析";
    }

    @Override
    public String getTypeName() {
        return "单项选择";
    }

    @Override
    public String getType() {
        return "K01";
    }
}