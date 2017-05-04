package com.lancoo.tasker.content.timu;

/**
 * Author: Andecy
 * Time: 2017/4/13
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface IAudioInfo {

    /**
     * @return 音频标题
     */
    String getAudioTitle();

    /**
     * @return 音频路径
     */
    String getAudioUrl();

    /**
     * @return 音频时长 单位：秒(未知请传0)
     */
    int getPlayTime();
}
