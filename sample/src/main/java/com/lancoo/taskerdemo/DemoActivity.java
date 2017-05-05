package com.lancoo.taskerdemo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.blankj.utilcode.util.ToastUtils;
import com.lancoo.tasker.TaskView;
import com.lancoo.taskerdemo.model.answer.KSTaskAnswer;
import com.lancoo.taskerdemo.model.timu.KSTaskTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DemoActivity extends FragmentActivity
        implements CompoundButton.OnCheckedChangeListener {

    private TaskView mTaskView;

    private int position;

    private int itemPosition;

    private DemoItemAdapter mDemoItemAdapter;

    private ToggleButton switch_answerable;
    private ToggleButton switch_standardable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mTaskView = (TaskView) findViewById(R.id.tasker_demo);
        switch_answerable = (ToggleButton) findViewById(R.id.tbtn_answerable);
        switch_standardable = (ToggleButton) findViewById(R.id.tbtn_standardable);

        switch_answerable.setOnCheckedChangeListener(this);
        switch_standardable.setOnCheckedChangeListener(this);

        mDemoItemAdapter = new DemoItemAdapter(getSupportFragmentManager(),
                new KSTaskTimu(), new KSTaskAnswer());

        mDemoItemAdapter.setAnswerable(switch_answerable.isChecked());
        mDemoItemAdapter.setAnalysisable(switch_standardable.isChecked());

        mTaskView.setItemAdapter(mDemoItemAdapter);

        mTaskView.setTaskTitleName("(试卷总分：5.0分)");

        mTaskView.setTaskListener(new TaskView.TaskListener() {

            @Override
            public void onTimuChanged(int topicPosition, int itemPosition) {
                Log.w("TAG", "topicPos-->" + topicPosition + ",itemPos-->" + itemPosition);
            }

            @Override
            public void onAudioPlayError(MediaPlayer mp, int what, int extra) {
                ToastUtils.showLongToast("播放出错");
            }

        });

        mTaskView.setOnItemSwitchClickListener(new TaskView.ItemSwitchListener() {
            @Override
            public void onItemSwitcherClick(View view) {
                mTaskView.showItemSelectView();
                ToastUtils.showLongToast("点击小题切换啦");
            }
        });
    }

    public void onForwardClick(View view) {
        mTaskView.changeTopicPosition(--position);
        Log.w("Demo", "topicPos-->" + position);
        itemPosition = 0;
    }

    public void onBackWorkClick(View view) {
        mTaskView.changeTopicPosition(++position);
        Log.w("Demo", "topicPos-->" + position);
        itemPosition = 0;
    }

    public void onForwardItemClick(View view) {
        mTaskView.changeItemPosition(--itemPosition);
        Log.w("Demo", "itemPos-->" + itemPosition);
    }

    public void onBackWorkItemClick(View view) {
        mTaskView.changeItemPosition(++itemPosition);
        Log.w("Demo", "itemPos-->" + itemPosition);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.tbtn_answerable:
                mDemoItemAdapter.setAnswerable(isChecked);
                break;
            case R.id.tbtn_standardable:
                mDemoItemAdapter.setAnalysisable(isChecked);
                break;
        }
    }

    public void onTopicListClick(View view) {
        mTaskView.showTopicSelectView();
    }
}
