package com.lancoo.taskerdemo.tasker.model.answer;

import com.lancoo.tasker.content.answer.ITaskAnswer;
import com.lancoo.tasker.content.answer.ITopicAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTaskAnswer implements ITaskAnswer {
    private List<ITopicAnswer> mKSTopicAnswers;

    @Override
    public String getUserId() {
        return "123123123";
    }

    @Override
    public List<ITopicAnswer> geTopicAnswers() {
        if (mKSTopicAnswers==null){
            mKSTopicAnswers = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                mKSTopicAnswers.add(new KSTopicAnswer());
            }
        }

        return mKSTopicAnswers;
    }
}
