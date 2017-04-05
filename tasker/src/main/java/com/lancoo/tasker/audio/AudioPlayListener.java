package com.lancoo.tasker.audio;

import android.media.MediaPlayer;

/**
 * Version 1.3
 * Create in 2016年1月25日下午8:46:58 by DoubleMeng Liu.
 * If you have any suggestions, send e-mail to abc@doulemeng.com.
 */
public interface AudioPlayListener{
	void onAudioPlayPrepare();
	void onAudioPlayPrepared(MediaPlayer mp);
	void onAudioPlayBufferingUpdate(MediaPlayer mp, int percent);
	void onAudioPlayUpdate(MediaPlayer mp);
	void onAudioPlayStart();
	void onAudioPlayPause();
	void onAudioPlayStop();
	void onAudioPlayCompletion();
	void onAudioPlayError(MediaPlayer mp, int what, int extra);
}
