package com.lancoo.tasker.timulist;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lancoo.tasker.adapter.BaseRecyclerViewAdapter;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.TopicAnswer;
import com.lancoo.tasker.content.timu.TopicTimu;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/28
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicListAdapter extends BaseRecyclerViewAdapter<TopicTimu> {
    private List<TopicAnswer> mTopicAnswers;
    private int curTopicPosition;

    public TopicListAdapter(@Nullable List<TopicTimu> dataList, List<TopicAnswer> topicAnswers, int curTopicPosition) {
        super(dataList);
        mTopicAnswers = topicAnswers;
        this.curTopicPosition = curTopicPosition;
    }

    @NonNull
    @Override
    public BaseRecylerItem<TopicTimu> createItem() {
        return new TopicListItem(mTopicAnswers, curTopicPosition);
    }
}
