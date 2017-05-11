package com.lancoo.taskerdemo.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.lancoo.lancooimageloader.LancooImageLoader;
import com.lancoo.lancooimageloader.LancooImageOptions;
import com.lancoo.taskerdemo.R;

/**
 * Author: Andecy
 * Time: 2017/5/11
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class ImageDemoActivity extends Activity {


    private ImageView iv_demo_loader;

    public static void start(Context context) {
        Intent starter = new Intent(context, ImageDemoActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_image);
        initView();
    }

    private void initView() {
        iv_demo_loader = (ImageView) findViewById(R.id.iv_demo_loader);

        String url = "http://upload.cbg.cn/2016/0316/1458097709566.gif";

//        x.image().bind(iv_demo_loader, url, new ImageOptions.Builder()
//                .setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher))
//                .setIgnoreGif(false)
//                .build());

        LancooImageLoader.bind(iv_demo_loader, url, new LancooImageOptions.Builder()
                .setIgnoreGif(false)
                .setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher))
                .build());
    }
}
