package com.lancoo.tasker.timulist;

import android.view.View;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.TopicAnswer;
import com.lancoo.tasker.content.timu.TopicTimu;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/5/3
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicListItem implements BaseRecylerItem<TopicTimu> {
    private List<TopicAnswer> mTopicAnswers;
    private int curPosition;

    private TextView tv_name;


    public TopicListItem(List<TopicAnswer> topicAnswers, int curPosition) {
        mTopicAnswers = topicAnswers;
        this.curPosition = curPosition;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.tasker_item_number;
    }

    @Override
    public void bindViews(View view) {
        tv_name = (TextView) view.findViewById(R.id.tv_item_player_name);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(TopicTimu data, int position) {
        tv_name.setText(data.getTypeName());
    }
}
