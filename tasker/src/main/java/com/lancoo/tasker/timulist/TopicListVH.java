package com.lancoo.tasker.timulist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.audio.SingleItemClickListener;
import com.lancoo.tasker.content.answer.TopicAnswer;
import com.lancoo.tasker.content.timu.TopicTimu;

/**
 * Author: Andecy
 * Time: 2017/4/14
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TopicListVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;

    private TextView tv_name;
    private TextView tv_time;
    private ImageView iv_cd;
    private TextView tv_no;
    private ImageView iv_status;

    private int mCurTopicPosition;
    private SingleItemClickListener mItemClickListener;

    private int curPosition;

    public TopicListVH(View itemView, int curTopicPosition, SingleItemClickListener singleItemClickListener) {
        super(itemView);
        mContext = itemView.getContext();
        mItemClickListener = singleItemClickListener;
        mCurTopicPosition = curTopicPosition;
        tv_name = (TextView) itemView.findViewById(R.id.tv_item_player_name);
        tv_time = (TextView) itemView.findViewById(R.id.tv_item_player_time);
        iv_status = (ImageView) itemView.findViewById(R.id.iv_item_player_status);
        iv_cd = (ImageView) itemView.findViewById(R.id.iv_item_player_cd);
        tv_no = (TextView) itemView.findViewById(R.id.tv_item_player_no);

        itemView.setOnClickListener(this);
    }

    public void handleData(TopicTimu topicTimu, TopicAnswer topicAnswer, int position) {

    }

    public static TopicListVH getInstance(ViewGroup parent, int curTopicPosition, SingleItemClickListener singleItemClickListener) {
        return new TopicListVH(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.tasker_item_player_list, parent, false), curTopicPosition, singleItemClickListener);
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemOnClick(v, curPosition);
        }
    }
}
