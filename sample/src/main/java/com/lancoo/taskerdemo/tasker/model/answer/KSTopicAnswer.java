package com.lancoo.taskerdemo.tasker.model.answer;

import com.lancoo.tasker.content.answer.IItemAnswer;
import com.lancoo.tasker.content.answer.ITopicAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTopicAnswer implements ITopicAnswer {
    private List<IItemAnswer> mItemAnswers;

    @Override
    public List<IItemAnswer> getItemAnswers() {
        if (mItemAnswers == null) {
            mItemAnswers = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                mItemAnswers.add(new KSItemAnswer());
            }
        }

        return mItemAnswers;
    }
}
