package com.lancoo.tasker.module.timu;

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

    String getAudioUrl();
}
