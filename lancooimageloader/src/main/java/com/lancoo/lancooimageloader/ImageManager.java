package com.lancoo.lancooimageloader;

import android.widget.ImageView;

/**
 * Author: Andecy
 * Time: 2017/5/11
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public interface ImageManager {

    void bind(ImageView view, String url);

    void bind(ImageView view, String url, LancooImageOptions options);

//    void bind(ImageView view, String url, Callback.CommonCallback<Drawable> callback);
//
//    void bind(ImageView view, String url, LancooImageOptions options, Callback.CommonCallback<Drawable> callback);
//
//    Callback.Cancelable loadDrawable(String url, LancooImageOptions options, Callback.CommonCallback<Drawable> callback);
//
//    Callback.Cancelable loadFile(String url, LancooImageOptions options, Callback.CacheCallback<File> callback);

    void clearMemCache();

    void clearCacheFiles();
}
