package com.lancoo.tasker.item;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Author: Andecy
 * Time: 2017/3/2
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public abstract class TBaseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentViewId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews();
        initView(view, savedInstanceState);
    }

    abstract protected void findViews();

    abstract protected void initView(View view, @Nullable Bundle savedInstanceState);


    protected <T extends View> T findView(@IdRes int viewId) {
        return (T) getView().findViewById(viewId);
    }

    public void release() {
    }

    @Override
    public void onDestroy() {
        release();
        super.onDestroy();
    }


    //-------------Fragment拦截返回键---------------------
    @Override
    public void onResume() {
        super.onResume();
        View view;
        if ((view = getView()) != null) {
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                        onBackPressed();
                        return true;
                    }
                    return false;
                }
            });
        }
    }

    public void onBackPressed() {
        getActivity().onBackPressed();
    }
    //----------------------------------------------------


    //-------------Fragment支持抽屉覆盖(默认不支持)----------

    /**
     * @return 是否支持
     */
    public boolean isViewInvisible() {
        return false;
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (getView() != null && isViewInvisible()) {
            getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
    //-----------------------------------------------------

    @LayoutRes
    abstract protected int getContentViewId();
}
