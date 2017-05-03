package com.lancoo.taskerdemo.model.answer;

import com.lancoo.tasker.content.answer.ItemAnswer;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSItemAnswer implements ItemAnswer {
    private String answer;

    @Override
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String getAnswer() {
        return answer;
    }
}
