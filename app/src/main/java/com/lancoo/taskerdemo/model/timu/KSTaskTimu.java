package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.module.timu.TaskTimu;
import com.lancoo.tasker.module.timu.TopicTimu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTaskTimu implements TaskTimu {
    @Override
    public List<TopicTimu> getTopicTimus() {
        List<TopicTimu> topicTimus = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            topicTimus.add(new KSTopicTimu());
        }
        return topicTimus;
    }

    @Override
    public float getScore() {
        return 10f;
    }

    @Override
    public String getTaskName() {
        return "带来了";
    }

}
