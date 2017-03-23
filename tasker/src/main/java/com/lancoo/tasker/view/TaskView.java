package com.lancoo.tasker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.UITool;
import com.lancoo.tasker.adapter.BaseItemAdapter;
import com.lancoo.tasker.module.TaskData;
import com.lancoo.tasker.module.answer.TaskAnswer;
import com.lancoo.tasker.module.timu.TaskTimu;
import com.lancoo.tasker.module.timu.TopicTimu;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/22
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TaskView extends LinearLayout {

    //header of task
    private TextView tv_score;
    private TextView tv_name;
    private TextView tv_count;
    private TextView tv_topicno;

    //player
    private LinearLayout ll_player;
    private TextView tv_player;
    private SeekBar sb_player;
    private AppCompatImageView btn_player;
//    private AudioPlayer mAudioPlayer;

    //大题内容
    private TextView tv_content;

    //分页
    private SplitView mSplitView;

    //小题
    private ScrollViewPager vp_item;
    private TextView tv_type;
    private TextView tv_itemNo;
    private TextView tv_itemCount;

    private List<TaskListener> mListeners;

    private BaseItemAdapter mItemAdapter;

    private TaskData mTaskData;

    private int curTopicPosition;

    public TaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.content_tasker, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TaskView);
        a.recycle();

        //header of task
        tv_score = findView(R.id.tv_tasker_score);
        tv_name = findView(R.id.tv_tasker_name);
        tv_count = findView(R.id.tv_tasker_count);
        tv_topicno = findView(R.id.tv_tasker_topicno);

        //player
        ll_player = findView(R.id.ll_module_player);
        tv_player = findView(R.id.tv_player_time);
        sb_player = findView(R.id.sb_player_progress);
        btn_player = findView(R.id.btn_player_start);

        //大题内容
        tv_content = findView(R.id.tv_tasker_topic_content);

        //分页
        mSplitView = findView(R.id.split_tasker);

        //小题
        vp_item = findView(R.id.vp_tasker_item);
        tv_type = findView(R.id.tv_tasker_topic_type);
        tv_itemNo = findView(R.id.tv_tasker_topic_item_no);
        tv_itemCount = findView(R.id.tv_tasker_topic_item_count);
    }

    private <T extends View> T findView(@IdRes int viewId) {
        return (T) findViewById(viewId);
    }


    private void injectData(@NonNull TaskData data) {
        if (data == null || data.getTaskAnswer() == null || data.getTaskTimu() == null) {
            return;
        }
        TaskTimu taskTimu = data.getTaskTimu();
        TaskAnswer taskAnswer = data.getTaskAnswer();
        setTaskInfo(taskTimu);

        setTopicInfo(0);
    }

    /**
     * 设置作业信息
     *
     * @param taskTimu 作业信息类
     */
    protected void setTaskInfo(TaskTimu taskTimu) {
        tv_score.setText("(总分：" + taskTimu.getTaskScore() + ")");
        tv_name.setText(taskTimu.getTaskName());
        if (taskTimu.getTopicTimus() == null) {
            return;
        }
        tv_count.setText("/" + taskTimu.getTopicTimus().size());
    }

    /**
     * 设置大题信息(通用)
     *
     * @param position 大题位置
     */
    protected void setTopicInfo(int position) {
        curTopicPosition = position;
        TopicTimu topicTimu = mTaskData.getTaskTimu().getTopicTimus().get(position);
        tv_topicno.setText("" + (curTopicPosition + 1));
        if (mItemAdapter != null) {
            vp_item.setAdapter(mItemAdapter);
        }

        tv_type.setText("(" + topicTimu.getTypeName() + ")");
        if (TextUtils.isEmpty(topicTimu.getContent()) && TextUtils.isEmpty(topicTimu.getAudioUrl())) {
            mSplitView.setSplitRatio(0);
        } else {
            mSplitView.setSplitRatio(0.3f);
            UITool.setRichTitle(topicTimu.getContent(), tv_content);
        }
    }

    public void addTaskListener(@NonNull TaskListener listener) {
        if (listener == null) {
            return;
        }
        if (mListeners == null) {
            mListeners = new ArrayList<TaskListener>();
        }
        mListeners.add(listener);
    }

    public void setItemAdapter(@NonNull BaseItemAdapter adapter) {
        if (adapter != null && adapter.getData() != null) {
            mItemAdapter = adapter;
            mTaskData = mItemAdapter.getData();
            injectData(mTaskData);
            invalidate();
        }

    }

    public interface TaskListener {

        void onTaskRenderFinished();

        void onItemChanged(int topicPosition, int itemPosition, String oldAnswer, String newAnswer);
    }

    public abstract static class SimpleTaskListener implements TaskListener {
        @Override
        public void onTaskRenderFinished() {
        }

        @Override
        public void onItemChanged(int topicPosition, int itemPosition, String oldAnswer, String newAnswer) {

        }
    }
}
