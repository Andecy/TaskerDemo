package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.module.timu.AudioInfo;
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
    public List<AudioInfo> getAudioInfos() {
        List<AudioInfo> audioInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            AudioInfo info = new AudioInfo() {
                @Override
                public String getAudioTitle() {
                    return "info:" + System.currentTimeMillis();
                }

                @Override
                public String getAudioUrl() {
                    return "http://www.baidu.com/baidu.mp3";
                }

                @Override
                public int getPlayTime() {
                    return 321;
                }
            };
            audioInfos.add(info);
        }

        return audioInfos;
    }

    @Override
    public float getScore() {
        return 2f;
    }
}
