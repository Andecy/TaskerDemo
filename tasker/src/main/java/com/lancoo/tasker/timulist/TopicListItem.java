package com.lancoo.tasker.timulist;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.Utils;
import com.lancoo.tasker.R;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.TopicAnswer;
import com.lancoo.tasker.content.timu.TopicTimu;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: Andecy
 * Time: 2017/5/3
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicListItem implements BaseRecylerItem<TopicTimu> {
    private List<TopicAnswer> mTopicAnswers;
    private int curPosition;

    private TextView tv_title;
    private TextView tv_count;
    private TextView tv_topicno;
    private CircleImageView civ_topicno;
    private ImageView iv_indicator;


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
        tv_title = (TextView) view.findViewById(R.id.tv_item_dotask_topicno_title);
        tv_count = (TextView) view.findViewById(R.id.tv_item_dotask_topicno_count);
        tv_topicno = (TextView) view.findViewById(R.id.tv_item_dotask_topicno);
        civ_topicno = (CircleImageView) view.findViewById(R.id.civ_item_dotask_topicno);
        iv_indicator = (ImageView) view.findViewById(R.id.iv_item_dotask_indicator);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(TopicTimu data, int position) {
        tv_title.setText(data.getTypeName());
        tv_topicno.setText("" + (position + 1));

        TopicAnswer topicAnswer = mTopicAnswers.get(position);

        tv_count.setText(topicAnswer.getItemAnswerFinishedCount() + "/" + topicAnswer.getItemAnswers().size());


        if (topicAnswer.getItemAnswerFinishedCount() == mTopicAnswers.get(position).getItemAnswers().size()) {
            civ_topicno.setImageResource(android.R.color.transparent);
            tv_topicno.setTextColor(Color.WHITE);
        } else {
            civ_topicno.setImageResource(R.color.default_background);
            tv_topicno.setTextColor(Utils.getContext().getResources().getColor(R.color.accent));
        }

        iv_indicator.setVisibility(curPosition == position ? View.VISIBLE : View.INVISIBLE);
    }
}
