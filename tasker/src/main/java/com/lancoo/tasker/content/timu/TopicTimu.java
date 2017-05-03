package com.lancoo.tasker.content.timu;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface TopicTimu {

    List<ItemTimu> getItemTimus();

    String getTypeName();

    String getContent();

    List<AudioInfo> getAudioInfos();

    float getScore();
}
