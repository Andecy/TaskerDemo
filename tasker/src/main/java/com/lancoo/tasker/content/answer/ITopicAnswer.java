package com.lancoo.tasker.content.answer;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface ITopicAnswer {

    List<? extends IItemAnswer> getItemAnswers();

    /**
     * @return 小题完成数量
     */
    int getItemAnswerFinishedCount();
}
