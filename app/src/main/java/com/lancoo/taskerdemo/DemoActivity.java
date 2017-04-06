package com.lancoo.taskerdemo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.lancoo.tasker.adapter.SimpleItemAdapter;
import com.lancoo.tasker.module.TaskData;
import com.lancoo.tasker.view.TaskView;
import com.lancoo.taskerdemo.model.answer.KSTaskAnswer;
import com.lancoo.taskerdemo.model.timu.KSTaskTimu;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DemoActivity extends AppCompatActivity {
    private TaskView mTaskView;

    private int position;

    private int itemPosition;

    private SimpleItemAdapter mSimpleItemAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

        mTaskView = (TaskView) findViewById(R.id.tasker_demo);

        mSimpleItemAdapter = new SimpleItemAdapter(
                getSupportFragmentManager(),
                new TaskData(new KSTaskTimu(), new KSTaskAnswer()));

        mSimpleItemAdapter.setAnswerable(false);

        mTaskView.setItemAdapter(mSimpleItemAdapter);

        mTaskView.setTaskTitleName("(试卷总分：5.0分)");

        mTaskView.addTaskListener(new TaskView.TaskListener() {
            @Override
            public void onTaskRenderFinished() {

            }

            @Override
            public void onTimuChanged(int topicPosition, int itemPosition) {
                Log.w("TAG", "topicPos-->" + topicPosition + ",itemPos-->" + itemPosition);
            }

            @Override
            public void onAudioPlayError(MediaPlayer mp, int what, int extra) {

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

    public void onAnswerableClick(View view) {
        if (mSimpleItemAdapter != null) {
            mSimpleItemAdapter.setAnswerable(!mSimpleItemAdapter.isAnswerable());
        }
    }

    public void onStandardableClick(View view) {
        if (mSimpleItemAdapter != null) {
            mSimpleItemAdapter.setStandardable(!mSimpleItemAdapter.isStandardable());
        }
    }
}
