package com.lancoo.tasker.timulist;

import android.content.Context;

import com.lancoo.tasker.content.TaskData;


/**
 * Author: Andecy
 * Time: 2017/4/27
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicPopupWindow extends BaseTimuListPopupWindow {

    public TopicPopupWindow(Context context, TaskData data, int curTopicPosition) {
        super(context,curTopicPosition);

        setAdapter(new TopicListAdapter(data.getTaskTimu().getTopicTimus(), data.getTaskAnswer().geTopicAnswers(), curTopicPosition));
    }
}
