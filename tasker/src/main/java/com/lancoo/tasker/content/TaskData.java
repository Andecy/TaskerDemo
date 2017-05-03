package com.lancoo.tasker.content;

import com.lancoo.tasker.content.answer.TaskAnswer;
import com.lancoo.tasker.content.timu.TaskTimu;

/**
 * Author: Andecy
 * Time: 2017/3/22
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TaskData {

    private TaskTimu mTaskTimu;
    private TaskAnswer mTaskAnswer;

    public TaskData(TaskTimu taskTimu, TaskAnswer taskAnswer) {
        mTaskTimu = taskTimu;
        mTaskAnswer = taskAnswer;
    }

    public TaskTimu getTaskTimu() {
        return mTaskTimu;
    }

    public TaskAnswer getTaskAnswer() {
        return mTaskAnswer;
    }
}
