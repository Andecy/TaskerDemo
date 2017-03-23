package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.module.timu.ItemTimu;
import com.lancoo.tasker.module.timu.TopicTimu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTopicTimu implements TopicTimu {
    @Override
    public List<ItemTimu> getItemTimus() {
        List<ItemTimu> itemTimus = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            itemTimus.add(new KSItemTimu());
        }
        return itemTimus;
    }

    @Override
    public String getTypeName() {
        return "高端题目";
    }

    @Override
    public String getContent() {
        return "呵呵哒";
    }

    @Override
    public String getAudioUrl() {
        return null;
    }
}
