package com.lancoo.taskerdemo;

import android.app.Application;

import com.lancoo.tasker.Tasker;

/**
 * Author: Andecy
 * Time: 2017/3/23
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Tasker.init(this);
    }
}
