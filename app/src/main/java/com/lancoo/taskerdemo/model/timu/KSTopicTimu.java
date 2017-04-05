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
            itemTimus.add(new KSItemTimu(content + "的小题" + i));
        }
        return itemTimus;
    }

    private String content;

    public KSTopicTimu(String content) {
        this.content = content;
    }

    @Override
    public String getTypeName() {
        return "高端大题";
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public String getAudioUrl() {
        return null;
    }

    @Override
    public float getScore() {
        return 2f;
    }
}
