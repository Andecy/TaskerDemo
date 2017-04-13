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
    private List<TopicAnswer> mKSTopicAnswers;

    @Override
    public String getUserId() {
        return "123123123";
    }

    @Override
    public List<TopicAnswer> geTopicAnswers() {
        if (mKSTopicAnswers==null){
            mKSTopicAnswers = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                mKSTopicAnswers.add(new KSTopicAnswer());
            }
        }

        return mKSTopicAnswers;
    }
}
