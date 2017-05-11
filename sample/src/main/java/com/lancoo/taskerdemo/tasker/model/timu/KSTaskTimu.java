package com.lancoo.taskerdemo.tasker.model.timu;

import com.lancoo.tasker.content.timu.ITaskTimu;
import com.lancoo.tasker.content.timu.ITopicTimu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTaskTimu implements ITaskTimu {
    private List<ITopicTimu> mTopicTimus;

    @Override
    public List<ITopicTimu> getTopicTimus() {
        if (mTopicTimus == null) {
            mTopicTimus = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                mTopicTimus.add(new KSTopicTimu("大题题干" + i));
            }
        }
        return mTopicTimus;
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
