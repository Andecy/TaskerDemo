package com.lancoo.lancooimageloader;

import android.graphics.drawable.Drawable;

/**
 * Author: Andecy
 * Time: 2017/5/11
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class LancooImageOptions {
    private OptionConverter converter;

    private boolean ignoreGif = true;

    private int loadingDrawableId;
    private int failureDrawableId;
    private Drawable loadingDrawable;
    private Drawable failureDrawable;

    public int getLoadingDrawableId() {
        return loadingDrawableId;
    }

    public int getFailureDrawableId() {
        return failureDrawableId;
    }

    public Drawable getLoadingDrawable() {
        return loadingDrawable;
    }

    public Drawable getFailureDrawable() {
        return failureDrawable;
    }

    public boolean isIgnoreGif() {
        return ignoreGif;
    }

    public static class Builder {
        public enum LoaderType {
            XUTILS3
        }

        protected LancooImageOptions options;


        public Builder() {
            newImageOptions();
        }


        protected void newImageOptions() {
            options = new LancooImageOptions();
        }


        public LancooImageOptions build() {
            if (options.converter==null){
                options.converter = new xUtilsOptionConverter(options);
            }
            return options;
        }


        public Builder setIgnoreGif(boolean ignoreGif) {
            options.ignoreGif = ignoreGif;
            return this;
        }


        public Builder setLoadingDrawable(int loadingDrawableId) {
            this.options.loadingDrawableId = loadingDrawableId;
            return this;
        }

        public Builder setLoadingDrawable(Drawable loadingDrawable) {
            this.options.loadingDrawable = loadingDrawable;
            return this;
        }

        public Builder setFailureDrawable(int failureDrawableId) {
            this.options.failureDrawableId = failureDrawableId;
            return this;
        }

        public Builder setFailureDrawable(Drawable failureDrawable) {
            this.options.failureDrawable = failureDrawable;
            return this;
        }
    }

    public OptionConverter getConverter() {
        return converter;
    }
}
