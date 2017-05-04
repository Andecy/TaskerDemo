package com.lancoo.tasker.content.timu;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface ITaskTimu {

    /**
     * @return 大题
     */
    List<? extends ITopicTimu> getTopicTimus();

    /**
     * @return 作业标准分
     */
    float getScore();

    /**
     * @return 作业名称
     */
    String getTaskName();

}
