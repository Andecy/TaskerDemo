package com.lancoo.tasker.event;

/**
 * Author: Andecy
 * Time: 2017/3/24
 * Email: andecy@foxmail.com
 * Description: 小题作答事件
 */

public class AnswerEvent {
    private String oldAnswer;
    private String newAnswer;

    public AnswerEvent(String oldAnswer, String newAnswer) {
        this.oldAnswer = oldAnswer;
        this.newAnswer = newAnswer;
    }

    public String getOldAnswer() {
        return oldAnswer;
    }

    public String getNewAnswer() {
        return newAnswer;
    }
}
