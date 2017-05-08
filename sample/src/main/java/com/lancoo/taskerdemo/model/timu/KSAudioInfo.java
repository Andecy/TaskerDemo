package com.lancoo.taskerdemo.model.timu;

import com.lancoo.tasker.content.timu.IAudioInfo;

/**
 * Author: Andecy
 * Time: 2017/5/8
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class KSAudioInfo implements IAudioInfo{

    private String title;

    public KSAudioInfo(String title) {
        this.title = title;
    }

    public KSAudioInfo() {
    }

    @Override
    public String getAudioTitle() {
        return title;
    }

    @Override
    public String getAudioUrl() {
        return "http://www.baidu.com/" + getAudioTitle() + "baidu.mp3";
    }

    @Override
    public int getPlayTime() {
        return 0;
    }
}
