package com.lancoo.taskerdemo.model.answer;

import com.lancoo.tasker.module.answer.ItemAnswer;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSItemAnswer implements ItemAnswer {
    String answer;

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }
}
