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
        return "http://172.16.51.68:8022/lgftp/lgkyzy/kyItemAnswer/ZYFB201612022019428818057/4D2E2BB1-7C90-4E0A-89EA-CCF25E3B9ED2/sa00000893/tm201610201524012212534/tm201610201524012212534_All.wav";
    }

    @Override
    public float getScore() {
        return 2f;
    }
}
