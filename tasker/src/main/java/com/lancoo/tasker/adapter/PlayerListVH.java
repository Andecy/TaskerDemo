package com.lancoo.tasker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.module.timu.AudioInfo;

/**
 * Author: Andecy
 * Time: 2017/4/14
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class PlayerListVH extends RecyclerView.ViewHolder {
    private Context mContext;

    private TextView tv_name;

    public PlayerListVH(View itemView) {
        super(itemView);
        mContext = itemView.getContext();

        tv_name = (TextView) itemView.findViewById(R.id.tv_item_player_name);
    }

    public void handleData(AudioInfo info){
        tv_name.setText(info.getAudioTitle());
    }

    public static PlayerListVH getInstance(ViewGroup parent) {
        return new PlayerListVH(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.tasker_item_player_list, parent, false));
    }
}
