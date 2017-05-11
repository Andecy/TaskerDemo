package com.lancoo.taskerdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.lancoo.taskerdemo.image.ImageDemoActivity;
import com.lancoo.taskerdemo.tasker.TaskDemoActivity;

/**
 * Author: Andecy
 * Time: 2017/5/11
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class DemoActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
    }

    public void onTaskClick(View view) {
        TaskDemoActivity.start(this);
    }

    public void onImageClick(View view) {
        ImageDemoActivity.start(this);
    }
}
