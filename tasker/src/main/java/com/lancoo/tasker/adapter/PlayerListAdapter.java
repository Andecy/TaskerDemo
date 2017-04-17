package com.lancoo.tasker.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.lancoo.tasker.audio.AudioPlayer;
import com.lancoo.tasker.module.timu.AudioInfo;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/13
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListVH> {
    private List<AudioInfo> mAudioInfos;
    private AudioPlayer mAudioPlayer;

    public PlayerListAdapter(AudioPlayer audioPlayer, List<AudioInfo> audioInfos) {
        mAudioInfos = audioInfos;
        mAudioPlayer = audioPlayer;
    }

    @Override
    public PlayerListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return PlayerListVH.getInstance(parent, mAudioPlayer);
    }

    @Override
    public void onBindViewHolder(PlayerListVH holder, int position) {
        holder.handleData(mAudioInfos.get(position));
    }


    @Override
    public int getItemCount() {
        return mAudioInfos.size();
    }
}
