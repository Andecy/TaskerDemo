package com.lancoo.lancooimageloader;

import android.app.Application;
import android.widget.ImageView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * Author: Andecy
 * Time: 2017/5/11
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class LancooImageLoader {

    public static void init(Application application) {
        x.Ext.init(application);
    }


    public static void bind(ImageView view, String url) {
        x.image().bind(view, url);
    }

    public static void bind(ImageView view, String url, LancooImageOptions options) {
        OptionConverter converter = options.getConverter();
        if (converter.getImageOptions() instanceof ImageOptions) {
            x.image().bind(view, url, (ImageOptions) converter.getImageOptions());
        }
    }

    public static void clearMemCache() {
        x.image().clearMemCache();
    }

    public static void clearCacheFiles() {
        x.image().clearCacheFiles();
    }
}
