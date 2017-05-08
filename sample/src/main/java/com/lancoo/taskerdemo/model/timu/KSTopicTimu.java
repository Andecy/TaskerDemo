package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.content.timu.IItemTimu;
import com.lancoo.tasker.content.timu.ITopicTimu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTopicTimu implements ITopicTimu {
    private List<KSAudioInfo> mAudioInfos;

    @Override
    public List<IItemTimu> getItemTimus() {
        List<IItemTimu> itemTimus = new ArrayList<>();
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
    public List<KSAudioInfo> getAudioInfos() {
        if (mAudioInfos == null) {
            mAudioInfos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                mAudioInfos.add(new KSAudioInfo("" + i));
            }
        }
        return mAudioInfos;
    }

    @Override
    public float getScore() {
        return 2f;
    }
}
