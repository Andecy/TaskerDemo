package com.lancoo.taskerdemo.model.answer;

import com.lancoo.tasker.module.answer.TaskAnswer;
import com.lancoo.tasker.module.answer.TopicAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTaskAnswer implements TaskAnswer {
    @Override
    public String getXh() {
        return "123123123";
    }

    @Override
    public List<TopicAnswer> geTopicAnswers() {
        List<TopicAnswer> topicAnswers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            topicAnswers.add(new KSTopicAnswer());
        }
        return topicAnswers;
    }
}
