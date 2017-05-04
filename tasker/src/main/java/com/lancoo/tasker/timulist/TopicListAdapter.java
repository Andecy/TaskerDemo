package com.lancoo.tasker.timulist;

import android.support.annotation.NonNull;

import com.lancoo.tasker.adapter.BaseRecyclerViewAdapter;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.ITopicAnswer;
import com.lancoo.tasker.content.timu.ITopicTimu;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/28
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicListAdapter extends BaseRecyclerViewAdapter<ITopicTimu> {
    private List<? extends ITopicAnswer> mTopicAnswers;
    private int curTopicPosition;

    public TopicListAdapter(List<? extends ITopicTimu> dataList, List<? extends ITopicAnswer> topicAnswers, int curTopicPosition) {
        super(dataList);
        mTopicAnswers = topicAnswers;
        this.curTopicPosition = curTopicPosition;
    }

    @NonNull
    @Override
    public BaseRecylerItem<ITopicTimu> createItem() {
        return new TopicListItem(mTopicAnswers, curTopicPosition);
    }
}
