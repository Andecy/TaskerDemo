package com.lancoo.taskerdemo.model.answer;

import com.lancoo.tasker.content.answer.ItemAnswer;
import com.lancoo.tasker.content.answer.TopicAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTopicAnswer implements TopicAnswer {
    private List<ItemAnswer> mItemAnswers;

    @Override
    public List<ItemAnswer> getItemAnswers() {
        if (mItemAnswers == null) {
            mItemAnswers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                mItemAnswers.add(new KSItemAnswer());
            }
        }

        return mItemAnswers;
    }

    @Override
    public int getItemAnswerFinishedCount() {
        return 9;
    }
}
