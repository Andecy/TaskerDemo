package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.content.timu.AudioInfo;
import com.lancoo.tasker.content.timu.ItemTimu;
import com.lancoo.tasker.content.timu.TopicTimu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSTopicTimu implements TopicTimu {
    private List<AudioInfo> mAudioInfos;

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
    public List<AudioInfo> getAudioInfos() {
        if (mAudioInfos == null) {
            mAudioInfos = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final int finalI = i;
                AudioInfo info = new AudioInfo() {
                    @Override
                    public String getAudioTitle() {
                        return "info:" + finalI;
                    }

                    @Override
                    public String getAudioUrl() {
                        return "http://www.baidu.com/" + getAudioTitle() + "baidu.mp3";
                    }

                    @Override
                    public int getPlayTime() {
                        return 321;
                    }
                };
                mAudioInfos.add(info);
            }
        }
        return mAudioInfos;
    }

    @Override
    public float getScore() {
        return 2f;
    }
}
