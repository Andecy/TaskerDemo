package com.lancoo.tasker.content.answer;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface ITaskAnswer {

    /**
     * @return 用户Id
     */
    String getUserId();

    /**
     * @return 大题作答
     */
    List<? extends ITopicAnswer> geTopicAnswers();


}
