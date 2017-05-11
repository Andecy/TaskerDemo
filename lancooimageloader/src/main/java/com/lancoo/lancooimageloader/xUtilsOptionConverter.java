package com.lancoo.lancooimageloader;

import org.xutils.image.ImageOptions;

/**
 * Author: Andecy
 * Time: 2017/5/11
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class xUtilsOptionConverter extends OptionConverter<ImageOptions> {
    private ImageOptions mImageOptions;

    public xUtilsOptionConverter(LancooImageOptions lancooImageOptions) {
        super(lancooImageOptions);
    }

    @Override
    public ImageOptions getImageOptions() {
        if (mImageOptions == null) {
            mImageOptions = new ImageOptions.Builder()
                    .setIgnoreGif(getLancooImageOptions().isIgnoreGif())
                    .setLoadingDrawable(getLancooImageOptions().getLoadingDrawable())
                    .setFailureDrawable(getLancooImageOptions().getFailureDrawable())
                    .setLoadingDrawableId(getLancooImageOptions().getLoadingDrawableId())
                    .setFailureDrawableId(getLancooImageOptions().getFailureDrawableId())
                    .build();
        }
        return mImageOptions;
    }


}
