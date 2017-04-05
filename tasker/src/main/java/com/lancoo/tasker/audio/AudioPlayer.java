package com.lancoo.tasker.audio;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.DrawableRes;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import java.io.IOException;

/**
 * Version 1.4 Create in 2015年11月30日下午9:31:36 by DoubleMeng Liu. If you have any
 * suggestions, send e-mail to abc@doulemeng.com.
 */
public class AudioPlayer extends MediaPlayer implements OnBufferingUpdateListener, OnPreparedListener,
        OnCompletionListener, OnErrorListener, OnSeekBarChangeListener {

    /**
     * 计时器控件
     */
    private TextView timer;
    /**
     * 计时格式
     */
    private String timeFormat = "mm:ss";
    /**
     * 拖动进度条
     */
    private SeekBar seekBar;
    /**
     * 普通进度条
     */
    private ProgressBar progressBar;
    /**
     * 这个类的状态监听接口
     */
    private AudioPlayListener audioPlayListener;
    /**
     * 当前资源标识
     */
    private String currentUri;
    /**
     * 计时器的指示图片
     */
    @DrawableRes
    private int timerIndicatorId;
    /**
     * 计时器的指示图片是否显示，仅用作内部判断
     */
    private boolean timerIndicatorShowing;
    /**
     * 播放器是否准备就绪，利用该状态可避免重复加载资源，提高播放器的响应速度。
     */
    private boolean prepared;
    /**
     * 计时器是否只显示当前播放时长,优先级比下个属性要高。
     */
    private boolean showCurTimeOnly;
    /**
     * 计时器是否只显示时长，指的是媒体资源总时长，目前仅对重置UI有效。
     */
    private boolean showDurationOnly;
    /**
     * 是否在准备就绪之后自动开始播放
     */
    private boolean autoPlay;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            if (isPlaying()) {
//                KLog.d(getCurrentPosition());
                if (audioPlayListener != null) {
                    audioPlayListener.onAudioPlayUpdate(AudioPlayer.this);
                }
                if (null != timer) {
                    timer.setText(showCurTimeOnly ? AudioTool.getDateFormatStr(AudioPlayer.this.getCurrentPosition(), timeFormat) : AudioTool.getDateFormatStr(AudioPlayer.this.getCurrentPosition(), timeFormat) + "/"
                            + AudioTool.getDateFormatStr(AudioPlayer.this.getDuration(), timeFormat));
                    if (timerIndicatorId != 0) {
                        timerIndicatorShowing = !timerIndicatorShowing;
                        timer.setCompoundDrawablesWithIntrinsicBounds(timerIndicatorShowing ? timerIndicatorId : 0, 0,
                                0, 0);
                    }
                }
                if (seekBar != null) {
                    seekBar.setProgress(AudioPlayer.this.getCurrentPosition());
                }
                if (progressBar != null) {
                    progressBar.setProgress(AudioPlayer.this.getCurrentPosition());
                }
                mHandler.sendEmptyMessageDelayed(0, 500);
            } else {
                if (timer != null && timerIndicatorId != 0) {
                    timerIndicatorShowing = false;
                    timer.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        }
    };

    public AudioPlayer() {
        this.setOnBufferingUpdateListener(this);
        this.setOnPreparedListener(this);
        this.setOnCompletionListener(this);
        this.setOnErrorListener(this);
    }

    public void bindTimer(TextView timer) {
        this.timer = timer;
    }

    public void setAudioPlayListener(AudioPlayListener audioPlayListener) {
        this.audioPlayListener = audioPlayListener;
    }

    public void bindSeekBar(SeekBar seekBar) {
        this.seekBar = seekBar;
        if (seekBar != null) {
            this.seekBar.setOnSeekBarChangeListener(this);
        }
    }

    public void bindProgressBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public boolean isPrepared() {
        return prepared;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public void setShowDurationOnly(boolean showDurationOnly) {
        this.showDurationOnly = showDurationOnly;
    }

    public void setAutoPlay(boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public String getCurrentUri() {
        return currentUri;
    }

    public void setTimerIndicatorId(int timerIndicatorId) {
        this.timerIndicatorId = timerIndicatorId;
    }

    public boolean isShowCurTimeOnly() {
        return showCurTimeOnly;
    }

    public void setShowCurTimeOnly(boolean showCurTimeOnly) {
        this.showCurTimeOnly = showCurTimeOnly;
    }

    /**
     * Dude,entrance is here
     *
     * @param uri
     */
    public void loadUri(String uri) {
        this.currentUri = uri;
        uri = AudioTool.uriEncode(uri);
        try {
            reset();
            setDataSource(uri);
            prepareAsync();
            if (audioPlayListener != null) {
                audioPlayListener.onAudioPlayPrepare();
            }
        } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseOrStart() {
        if (isPlaying()) {
            pause();
        } else {
            start();
        }
    }

    private void resetUi() {
        mHandler.removeMessages(0);
        if (seekBar != null) {
            seekBar.setProgress(0);
            seekBar.setSecondaryProgress(0);
            seekBar.setEnabled(false);
        }
        if (progressBar != null) {
            progressBar.setProgress(0);
        }
        if (null != timer) {
            if (showCurTimeOnly) {
                timer.setText("00:00");
            } else {
                timer.setText(showDurationOnly ? AudioTool.getDateFormatStr(getDuration(), timeFormat)
                        : "00:00/" + AudioTool.getDateFormatStr(getDuration(), timeFormat));
            }
            if (timerIndicatorId != 0) {
                timerIndicatorShowing = false;
                timer.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }
        }
    }

    /**
     * start super method region
     */
    @Override
    public void start() throws IllegalStateException {
        super.start();
        if (seekBar != null) {
            seekBar.setEnabled(true);
        }
        if (audioPlayListener != null) {
            audioPlayListener.onAudioPlayStart();
        }
        mHandler.removeMessages(0);
        mHandler.sendEmptyMessage(0);

    }

    @Override
    public void pause() throws IllegalStateException {
        super.pause();
        mHandler.removeMessages(0);
        if (timerIndicatorId != 0 && timer != null) {
            timer.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
        if (audioPlayListener != null) {
            audioPlayListener.onAudioPlayPause();
        }
    }

    @Override
    public void stop() throws IllegalStateException {
        super.stop();
        if (audioPlayListener != null) {
            audioPlayListener.onAudioPlayStop();
        }
        prepared = false;
        resetUi();
    }

    @Override
    public void release() {
        mHandler.removeCallbacksAndMessages(null);
        super.release();
    }

    public void fastForward() {
        int result = getCurrentPosition() + getDuration() / 10;
        if (result > getDuration()) {
            result = getDuration();
        }
//        if (seekBar != null) {
//            seekBar.setProgress(result);
//        }
        if (prepared) {
            seekTo(result);
        }
    }

    public void fastBackward() {
        int result = getCurrentPosition() - getDuration() / 10;
        if (result < 0) {
            result = 0;
        }
//        if (seekBar != null) {
//            seekBar.setProgress(result);
//        }
        if (prepared) {
            seekTo(result);
        }
    }


    /**
     * end super method region
     */

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        if (isPlaying()) {
            if (audioPlayListener != null) {
//                KLog.d(mp.getCurrentPosition());
                audioPlayListener.onAudioPlayBufferingUpdate(this, percent);
            }
            if (null != timer) {
                timer.setText(showCurTimeOnly ? AudioTool.getDateFormatStr(mp.getCurrentPosition(), timeFormat) : AudioTool.getDateFormatStr(mp.getCurrentPosition(), timeFormat) + "/"
                        + AudioTool.getDateFormatStr(mp.getDuration(), timeFormat));
            }
            if (seekBar != null) {
                seekBar.setSecondaryProgress(percent * mp.getDuration() / 100);
                seekBar.setProgress(mp.getCurrentPosition());
            }
            if (progressBar != null) {
                progressBar.setSecondaryProgress(percent * mp.getDuration() / 100);
                progressBar.setProgress(mp.getCurrentPosition());
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        if (audioPlayListener != null) {
            audioPlayListener.onAudioPlayPrepared(mp);
        }
        prepared = true;
        if (seekBar != null) {
            seekBar.setEnabled(true);
            seekBar.setMax(mp.getDuration());
        }
        if (progressBar != null) {
            progressBar.setMax(mp.getDuration());
        }
        if (autoPlay) {
            start();
        }
//        KLog.d(mp.getDuration());
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if (audioPlayListener != null) {
            audioPlayListener.onAudioPlayCompletion();
        }
        resetUi();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        if (audioPlayListener != null) {
            audioPlayListener.onAudioPlayError(mp, what, extra);
        }
        prepared = false;
        return true;
    }

    /**
     * start seekbar listener region
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//        if (prepared) {
//            seekTo(progress);
//        }
        if (null != timer && prepared) {
            timer.setText(showCurTimeOnly ? AudioTool.getDateFormatStr(progress, timeFormat) : AudioTool.getDateFormatStr(progress, timeFormat) + "/"
                    + AudioTool.getDateFormatStr(getDuration(), timeFormat));
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (prepared) {
            seekTo(seekBar.getProgress());
        }
    }
    /**
     * end seekbar listener region
     */

}
