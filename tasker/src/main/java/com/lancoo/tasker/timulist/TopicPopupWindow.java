package com.lancoo.tasker.timulist;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.TaskView;
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

public class TopicPopupWindow extends BaseTimuListPopupWindow implements View.OnClickListener {
    private TextView tv_submit;
    private TaskView.TaskListener mTaskListener;

    public TopicPopupWindow(Context context,
                            List<? extends ITopicTimu> dataList,
                            List<? extends ITopicAnswer> topicAnswers,
                            int curTopicPosition,
                            SingleItemClickListener listener,
                            TaskView.TaskListener taskListener) {
        super(context, listener);
        mTaskListener = taskListener;
        setAdapter(new TopicListAdapter(
                dataList,
                topicAnswers,
                curTopicPosition), new LinearLayoutManager(context));
    }

    @Override
    protected void findView(View view) {
        super.findView(view);
        tv_submit = (TextView) view.findViewById(R.id.tv_list_number_submit);
        tv_submit.setVisibility(View.VISIBLE);
        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mTaskListener != null) {
            mTaskListener.onTaskSubmitClick();
        }
    }
}
