package com.lancoo.tasker.timulist;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.lancoo.tasker.R;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.ITopicAnswer;
import com.lancoo.tasker.content.timu.ITopicTimu;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: Andecy
 * Time: 2017/5/3
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicListItem implements BaseRecylerItem<ITopicTimu> {
    private List<? extends ITopicAnswer> mTopicAnswers;
    private int[] mTopicAnswerCounts;
    private int curPosition;

    private TextView tv_title;
    private TextView tv_count;
    private TextView tv_no;
    private CircleImageView civ_no;
    private ImageView iv_indicator;


    public TopicListItem(List<? extends ITopicAnswer> topicAnswers, int curPosition) {
        mTopicAnswers = topicAnswers;
        this.curPosition = curPosition;
        mTopicAnswerCounts = new int[topicAnswers.size()];
        for (int i = 0; i < mTopicAnswers.size(); i++) {
            for (int j = 0; j < mTopicAnswers.get(i).getItemAnswers().size(); j++) {
                if (!TextUtils.isEmpty(mTopicAnswers.get(i).getItemAnswers().get(j).getAnswer())) {
                    mTopicAnswerCounts[i]++;
                }
            }

        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.tasker_item_number_topic;
    }

    @Override
    public void bindViews(View view) {
        tv_title = (TextView) view.findViewById(R.id.tv_item_number_title);
        tv_count = (TextView) view.findViewById(R.id.tv_item_number_count);
        tv_no = (TextView) view.findViewById(R.id.tv_item_number_no);
        civ_no = (CircleImageView) view.findViewById(R.id.civ_item_number_no);
        iv_indicator = (ImageView) view.findViewById(R.id.iv_item_number_indicator);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(ITopicTimu data, int position) {
        tv_title.setText(data.getTypeName());
        tv_no.setText("" + (position + 1));

        ITopicAnswer topicAnswer = mTopicAnswers.get(position);

        tv_count.setText(mTopicAnswerCounts[position] + "/" + topicAnswer.getItemAnswers().size());


        if (mTopicAnswerCounts[position] == mTopicAnswers.get(position).getItemAnswers().size()) {
            civ_no.setImageResource(android.R.color.transparent);
            tv_no.setTextColor(Color.WHITE);
        } else {
            civ_no.setImageResource(R.color.default_background);
            tv_no.setTextColor(Utils.getContext().getResources().getColor(R.color.accent));
        }

        iv_indicator.setVisibility(curPosition == position ? View.VISIBLE : View.INVISIBLE);
    }
}
