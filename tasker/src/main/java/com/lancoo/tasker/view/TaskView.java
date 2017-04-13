package com.lancoo.tasker.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.adapter.BaseItemAdapter;
import com.lancoo.tasker.audio.AudioPlayListener;
import com.lancoo.tasker.audio.AudioPlayer;
import com.lancoo.tasker.module.TaskData;
import com.lancoo.tasker.module.answer.TaskAnswer;
import com.lancoo.tasker.module.timu.AudioInfo;
import com.lancoo.tasker.module.timu.TaskTimu;
import com.lancoo.tasker.module.timu.TopicTimu;
import com.lancoo.tasker.tool.UITool;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/22
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TaskView extends LinearLayout implements AudioPlayListener, View.OnClickListener {

    private static final String TAG = "TaskView";

    //header of task
    private TextView tv_score;
    private TextView tv_name;
    private TextView tv_count;
    private TextView tv_topicno;

    //player
    private RelativeLayout rl_player;
    private TextView tv_player_time;
    private TextView tv_player_title;
    private SeekBar sb_player;
    private ImageView iv_player_start;
    private ImageView iv_player_list;
    private AudioPlayer mAudioPlayer;

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
        rl_player = findView(R.id.rl_module_player);
        tv_player_time = findView(R.id.tv_player_time);
        tv_player_title = findView(R.id.tv_player_title);
        sb_player = findView(R.id.sb_player_progress);
        iv_player_start = findView(R.id.iv_player_start);
        iv_player_list = findView(R.id.iv_player_list);
        iv_player_start.setOnClickListener(this);
        iv_player_list.setOnClickListener(this);


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
//        tv_score.setText();
        tv_name.setText(taskTimu.getTaskName());
        if (taskTimu.getTopicTimus() == null) {
            return;
        }
        tv_count.setText("/" + taskTimu.getTopicTimus().size());
    }

    /**
     * 设置整份作业的Title
     *
     * @param titleName 标题名称
     */
    public void setTaskTitleName(String titleName) {
        tv_score.setText(titleName);
        invalidate();
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
        if (TextUtils.isEmpty(topicTimu.getContent()) && topicTimu.getAudioInfos() == null) {
            mSplitView.setSplitRatio(0);
        } else {
            mSplitView.setSplitRatio(0.3f);
            UITool.setRichTitle(topicTimu.getContent(), tv_content);
        }

        //---player--------

        initPlayer(topicTimu.getAudioInfos().get(0));
    }


    private void setItemInfo(int position) {
        if (mItemAdapter == null ||
                mItemAdapter.getData() == null ||
                mItemAdapter.getData().getTaskTimu().getTopicTimus().get(curTopicPosition).getItemTimus().size() <= position) {

            return;
        }

        curItemPosition = position;

        Log.w(TAG, "curItemPosition-->" + curItemPosition);

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

    //===============================听力=====================

    private void initPlayer(AudioInfo info) {
        if (TextUtils.isEmpty(info.getAudioUrl())) {
            rl_player.setVisibility(GONE);
            return;
        }
        rl_player.setVisibility(VISIBLE);
        if (mAudioPlayer == null) {
            mAudioPlayer = new AudioPlayer();
            mAudioPlayer.setAudioPlayListener(this);
            mAudioPlayer.bindSeekBar(sb_player);
            mAudioPlayer.bindTimer(tv_player_time);
        }
        tv_player_title.setText(info.getAudioTitle());
        mAudioPlayer.loadUri(info.getAudioUrl());
    }

    @Override
    public void onAudioPlayPrepare() {
    }

    @Override
    public void onAudioPlayPrepared(MediaPlayer mp) {
        sb_player.setEnabled(true);
    }

    @Override
    public void onAudioPlayBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onAudioPlayUpdate(MediaPlayer mp) {

    }

    @Override
    public void onAudioPlayStart() {
        iv_player_start.setImageResource(R.mipmap.ic_player_pause2);
    }

    @Override
    public void onAudioPlayPause() {
        iv_player_start.setImageResource(R.mipmap.ic_player_start2);

    }

    @Override
    public void onAudioPlayStop() {
    }

    @Override
    public void onAudioPlayCompletion() {
        iv_player_start.setImageResource(R.mipmap.ic_player_start2);
    }

    @Override
    public void onAudioPlayError(MediaPlayer mp, int what, int extra) {
        iv_player_start.setImageResource(R.mipmap.ic_player_start2);
        if (mListeners != null) {
            int listenerCount = mListeners.size();
            for (int i = listenerCount - 1; i >= 0; i--) {
                mListeners.get(i).onAudioPlayError(mp, what, extra);
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_player_start) {
            if (mAudioPlayer == null || !mAudioPlayer.isPrepared()) {
                return;
            }
            mAudioPlayer.pauseOrStart();
        } else if (id == R.id.iv_player_list) {
            BottomSheetDialog dialog = new BottomSheetDialog(getContext());
            dialog.setContentView(R.layout.tasker_header);
            dialog.show();
        }

    }

    public interface TaskListener {

        void onTaskRenderFinished();

        void onTimuChanged(int topicPosition, int itemPosition);

        void onAudioPlayError(MediaPlayer mp, int what, int extra);
    }

    public abstract static class SimpleTaskListener implements TaskListener {
        @Override
        public void onTaskRenderFinished() {
        }

        @Override
        public void onAudioPlayError(MediaPlayer mp, int what, int extra) {

        }

        @Override
        public void onTimuChanged(int topicPosition, int itemPosition) {
        }
    }
}
