package com.lancoo.tasker.tool;

import android.text.TextUtils;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;
import com.zzhoujay.richtext.CacheType;
import com.zzhoujay.richtext.ImageHolder;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.SimpleImageFixCallback;

/**
 * Created by Qiang on 2016/10/12.
 * 邮箱：anWorkMail_q@126.com
 * 作用：修改UI的Tool
 */

public class UITool {

    private static int mScreenHeight;
    private static int mScreenWidth;

    /**
     * 富文本
     */
    public static void setRichTitle(String content, TextView textView) {
        if (textView == null) {
            return;
        }
        if (mScreenHeight == 0 || mScreenWidth == 0) {
            mScreenHeight = ScreenUtils.getScreenHeight();
            mScreenWidth = ScreenUtils.getScreenWidth();
        }
        if (TextUtils.isEmpty(content)) {
            content = "(暂无数据)";
        }
        RichText.fromHtml(content)
                .autoFix(false)
                .clickable(false)
                .cache(CacheType.NONE)
                .fix(new SimpleImageFixCallback() {
                    @Override
                    public void onImageReady(ImageHolder holder, int width, int height) {
                        super.onImageReady(holder, width, height);
                        if (height >= mScreenHeight || width >= mScreenWidth) {
                            holder.setHeight(mScreenHeight / 2);
                            holder.setWidth(mScreenWidth / 2);
                        }
                        holder.setAutoPlay(true);
                        holder.setAutoFix(true);
                    }
                })
                .into(textView);
    }
}
