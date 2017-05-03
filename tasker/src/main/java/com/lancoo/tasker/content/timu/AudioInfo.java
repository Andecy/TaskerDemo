package com.lancoo.tasker.content.timu;

/**
 * Author: Andecy
 * Time: 2017/4/13
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface AudioInfo {
    String getAudioTitle();

    String getAudioUrl();

    /**
     * 音频
     * @return 单位：秒
     */
    int getPlayTime();
}
