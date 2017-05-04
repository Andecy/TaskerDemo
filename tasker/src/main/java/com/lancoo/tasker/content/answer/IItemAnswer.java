package com.lancoo.tasker.content.answer;

import java.io.Serializable;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface IItemAnswer extends Serializable {

    /**
     * @param answer 作答答案
     */
    void setAnswer(String answer);

    /**
     * @return 作答答案
     */
    String getAnswer();
}
