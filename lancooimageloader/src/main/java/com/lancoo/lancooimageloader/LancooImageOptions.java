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

    // region ###################### decode options (equals & hashcode prop) ################
    private int width = 0; // 小于0时不采样压缩. 等于0时自动识别ImageView的宽高和maxWidth.
    private int height = 0; // 小于0时不采样压缩. 等于0时自动识别ImageView的宽高和maxHeight.
    private boolean ignoreGif = true;

    // region ############# display options
    private int loadingDrawableId;
    private int failureDrawableId;
    private Drawable loadingDrawable;
    private Drawable failureDrawable;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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

        protected LancooImageOptions options;


        public Builder() {
            newImageOptions();
        }


        protected void newImageOptions() {
            options = new LancooImageOptions();
        }


        public LancooImageOptions build() {
            if (options.converter == null) {
                options.converter = new xUtilsOptionConverter(options);
            }
            return options;
        }

        /**
         * 小于0时不采样压缩. 等于0时自动识别ImageView的宽高和(maxWidth, maxHeight).
         *
         * @param width
         * @param height
         * @return
         */
        public Builder setSize(int width, int height) {
            options.width = width;
            options.height = height;
            return this;
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
