package com.lancoo.tasker.view;

import android.app.Activity;
import android.content.Context;
import android.text.method.KeyListener;
import android.util.AttributeSet;

import com.blankj.utilcode.util.KeyboardUtils;

/**
 * 作者(Author): Andecy on 2016/2/21.
 * 邮箱(E-mail): Andecy@foxmail.com
 * 描述(Description): 可以控制是否可写的MaterialEditText
 */
public class WritableEditText extends android.support.v7.widget.AppCompatEditText {
    private KeyListener keyListener = getKeyListener();

    public WritableEditText(Context context) {
        super(context);
    }

    public WritableEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WritableEditText(Context context, AttributeSet attrs, int style) {
        super(context, attrs, style);
    }


    public void setEditable(Activity activity, boolean enabled) {
        setKeyListener(enabled ? keyListener : null);
        setFocusable(enabled);
        if (!enabled) {
            KeyboardUtils.hideSoftInput(activity);
        } else {
            KeyboardUtils.showSoftInput(this);
        }
    }

}
