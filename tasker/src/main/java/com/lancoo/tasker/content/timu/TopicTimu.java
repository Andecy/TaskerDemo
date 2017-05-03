package com.lancoo.tasker.content.timu;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface TopicTimu {

    /**
     * @return 大题所属的小题
     */
    List<ItemTimu> getItemTimus();

    /**
     * @return 大题题型
     */
    String getTypeName();

    /**
     * @return 大题题干
     */
    String getContent();

    /**
     * @return 音频信息
     */
    List<AudioInfo> getAudioInfos();

    /**
     * @return 大题分数
     */
    float getScore();
}
