package com.lancoo.lancooimageloader;

/**
 * Author: Andecy
 * Time: 2017/5/11
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public abstract class OptionConverter<T> {
    private LancooImageOptions mLancooImageOptions;

    public OptionConverter(LancooImageOptions lancooImageOptions) {
        mLancooImageOptions = lancooImageOptions;
    }


    public LancooImageOptions getLancooImageOptions() {
        return mLancooImageOptions;
    }

    public abstract T getImageOptions();
}
