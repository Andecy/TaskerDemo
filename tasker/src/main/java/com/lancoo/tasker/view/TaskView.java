package com.lancoo.tasker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
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
    private ViewPager vp_item;
    private TextView tv_type;
    private TextView tv_itemNo;
    private TextView tv_itemCount;

    private List<TaskListener> mListeners;

    private BaseItemAdapter mItemAdapter;

    private TaskData mTaskData;

    private int curTopicPosition;
    private int curItemPosition;

    private ViewPager.OnPageChangeListener itemChangeListener;

    public TaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tasker_content, this);

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
    private void setTaskInfo(TaskTimu taskTimu) {
        tv_score.setText("(作业总分：" + taskTimu.getScore() + ")");
        tv_name.setText(taskTimu.getTaskName());
        if (taskTimu.getTopicTimus() == null) {
            return;
        }
        tv_count.setText("/" + taskTimu.getTopicTimus().size());
    }

    /**
     * 设置大题信息
     *
     * @param position 大题位置
     */
    private void setTopicInfo(int position) {
        if (mItemAdapter == null ||
                mItemAdapter.getData() == null ||
                mItemAdapter.getData().getTaskTimu().getTopicTimus().size() <= position) {
            return;
        }

        curTopicPosition = position;

        setItemInfo(0);

        Log.w("TaskView", "curTopicPosition-->" + curTopicPosition);

        TopicTimu topicTimu = mTaskData.getTaskTimu().getTopicTimus().get(position);

        if (mListeners != null) {
            int listenerCount = mListeners.size();
            for (int i = listenerCount - 1; i >= 0; i--) {
                mListeners.get(i).onTimuChanged(curTopicPosition, curItemPosition);
            }
        }

        if (vp_item.getAdapter() != mItemAdapter) {
            vp_item.setAdapter(mItemAdapter);
        } else {
            //update adapter data
            mItemAdapter.update(curTopicPosition);
        }
        tv_topicno.setText("" + (curTopicPosition + 1));

        tv_itemCount.setText("/(" + topicTimu.getItemTimus().size() + ")");
        tv_type.setText("(大题总分：" + topicTimu.getScore() + "分)" + topicTimu.getTypeName());
        if (TextUtils.isEmpty(topicTimu.getContent()) && TextUtils.isEmpty(topicTimu.getAudioUrl())) {
            mSplitView.setSplitRatio(0);
        } else {
            mSplitView.setSplitRatio(0.3f);
            UITool.setRichTitle(topicTimu.getContent(), tv_content);
        }
    }


    private void setItemInfo(int position) {
        if (mItemAdapter == null ||
                mItemAdapter.getData() == null ||
                mItemAdapter.getData().getTaskTimu().getTopicTimus().get(curTopicPosition).getItemTimus().size() <= position) {

            return;
        }

        curItemPosition = position;

        Log.w("TaskView", "curItemPosition-->" + curItemPosition);

        vp_item.setCurrentItem(curItemPosition);
        tv_itemNo.setText("(" + (curItemPosition + 1) + ")");

        if (itemChangeListener == null) {
            itemChangeListener = new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    curItemPosition = position;
                    tv_itemNo.setText("(" + (position + 1) + ")");
                    if (mListeners != null) {
                        int listenerCount = mListeners.size();
                        for (int i = listenerCount - 1; i >= 0; i--) {
                            mListeners.get(i).onTimuChanged(curTopicPosition, curItemPosition);
                        }
                    }
                }
            };
            vp_item.addOnPageChangeListener(itemChangeListener);
        }

    }

    /**
     * 添加一个用于监听作业事件的Listener到Listener列表中.
     *
     * @param listener 要添加的监听器.
     * @see #removeTaskListener(TaskListener)
     */
    public void addTaskListener(@NonNull TaskListener listener) {
        if (listener == null) {
            return;
        }
        if (mListeners == null) {
            mListeners = new ArrayList<TaskListener>();
        }
        mListeners.add(listener);
    }

    /**
     * 从Listener列表中移除某个用于监听作业事件的Listener.
     *
     * @param listener 要移除的监听器
     * @see #addTaskListener(TaskListener)
     */
    public void removeTaskListener(@NonNull TaskListener listener) {
        if (listener == null) {
            return;
        }
        if (mListeners == null) {
            // This can happen if this method is called before the first call to addDrawerListener
            return;
        }
        mListeners.remove(listener);
    }

    public void setItemAdapter(@NonNull BaseItemAdapter adapter) {
        if (adapter != null && adapter.getData() != null) {
            mItemAdapter = adapter;
            mTaskData = mItemAdapter.getData();
            injectData(mTaskData);
            invalidate();
        }
    }

    public void changeTopicPosition(int position) {
        if (position < 0 || position == curTopicPosition) {
            return;
        }
        setTopicInfo(position);
        invalidate();
    }

    public void changeItemPosition(int position) {
        if (position < 0 || position == curItemPosition) {
            return;
        }
        setItemInfo(position);
        invalidate();
    }

    public interface TaskListener {

        void onTaskRenderFinished();

        void onTimuChanged(int topicPosition, int itemPosition);
    }

    public abstract static class SimpleTaskListener implements TaskListener {
        @Override
        public void onTaskRenderFinished() {
        }

        @Override
        public void onTimuChanged(int topicPosition, int itemPosition) {
        }
    }
}
