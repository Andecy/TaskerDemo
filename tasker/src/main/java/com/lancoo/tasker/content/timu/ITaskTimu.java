package com.lancoo.tasker.content.timu;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface ITaskTimu {

    List<? extends ITopicTimu> getTopicTimus();

    float getScore();

    String getTaskName();

}
