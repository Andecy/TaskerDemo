package com.lancoo.tasker.content;

import com.lancoo.tasker.content.answer.ITaskAnswer;
import com.lancoo.tasker.content.timu.ITaskTimu;

/**
 * Author: Andecy
 * Time: 2017/3/22
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TaskData {

    private ITaskTimu mTaskTimu;
    private ITaskAnswer mTaskAnswer;

    public TaskData(ITaskTimu taskTimu, ITaskAnswer taskAnswer) {
        mTaskTimu = taskTimu;
        mTaskAnswer = taskAnswer;
    }

    public ITaskTimu getTaskTimu() {
        return mTaskTimu;
    }

    public ITaskAnswer getTaskAnswer() {
        return mTaskAnswer;
    }
}
