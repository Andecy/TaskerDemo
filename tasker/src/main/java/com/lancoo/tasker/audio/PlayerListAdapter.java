package com.lancoo.tasker.audio;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.lancoo.tasker.adapter.BaseRecyclerViewAdapter;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.timu.IAudioInfo;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/4/13
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class PlayerListAdapter extends BaseRecyclerViewAdapter<IAudioInfo> {
    private AudioPlayer mAudioPlayer;

    public PlayerListAdapter(@Nullable List<IAudioInfo> dataList, AudioPlayer audioPlayer) {
        super(dataList);
        mAudioPlayer = audioPlayer;
    }

    @NonNull
    @Override
    public BaseRecylerItem<IAudioInfo> createItem() {
        return new PlayerListItem(mAudioPlayer);
    }
}
