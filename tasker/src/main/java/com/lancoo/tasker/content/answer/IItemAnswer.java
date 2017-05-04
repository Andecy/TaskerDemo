package com.lancoo.tasker.content.answer;

import java.io.Serializable;

/**
 * Author: Andecy
 * Time: 2017/3/1
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface IItemAnswer extends Serializable {

    void setAnswer(String answer);

    String getAnswer();
}
