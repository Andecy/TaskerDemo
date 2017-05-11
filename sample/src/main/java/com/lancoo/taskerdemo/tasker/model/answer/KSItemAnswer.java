package com.lancoo.taskerdemo.tasker.model.answer;

import com.lancoo.tasker.content.answer.IItemAnswer;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSItemAnswer implements IItemAnswer {
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
