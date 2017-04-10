package com.lancoo.taskerdemo.model.answer;

import com.lancoo.tasker.module.answer.ItemAnswer;
import com.lancoo.tasker.module.answer.TopicAnswer;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTopicAnswer implements TopicAnswer {
    @Override
    public List<ItemAnswer> getItemAnswers() {
        List<ItemAnswer> itemAnswers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemAnswers.add(new KSItemAnswer());
        }
        return itemAnswers;
    }
}
