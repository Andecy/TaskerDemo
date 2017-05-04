package com.lancoo.tasker.timulist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.lancoo.tasker.adapter.SingleItemClickListener;
import com.lancoo.tasker.content.answer.ITopicAnswer;
import com.lancoo.tasker.content.timu.ITopicTimu;

import java.util.List;


/**
 * Author: Andecy
 * Time: 2017/4/27
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicPopupWindow extends BaseTimuListPopupWindow {

    public TopicPopupWindow(Context context, List<? extends ITopicTimu> dataList, List<? extends ITopicAnswer> topicAnswers, int curTopicPosition, SingleItemClickListener listener) {
        super(context, curTopicPosition, listener);

        setAdapter(new TopicListAdapter(
                dataList,
                topicAnswers,
                curTopicPosition), new LinearLayoutManager(context));
    }
}
