package com.lancoo.tasker.audio;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.content.timu.AudioInfo;

/**
 * Author: Andecy
 * Time: 2017/4/14
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class PlayerListVH extends RecyclerView.ViewHolder implements View.OnClickListener {
    private Context mContext;

    private TextView tv_name;
    private TextView tv_time;
    private ImageView iv_cd;
    private TextView tv_no;
    private ImageView iv_status;

    private AudioPlayer mAudioPlayer;

    private SingleItemClickListener mItemClickListener;

    private int curPosition;

    public PlayerListVH(View itemView, AudioPlayer audioPlayer, SingleItemClickListener singleItemClickListener) {
        super(itemView);
        mContext = itemView.getContext();
        mAudioPlayer = audioPlayer;
        mItemClickListener = singleItemClickListener;

        tv_name = (TextView) itemView.findViewById(R.id.tv_item_player_name);
        tv_time = (TextView) itemView.findViewById(R.id.tv_item_player_time);
        iv_status = (ImageView) itemView.findViewById(R.id.iv_item_player_status);
        iv_cd = (ImageView) itemView.findViewById(R.id.iv_item_player_cd);
        tv_no = (TextView) itemView.findViewById(R.id.tv_item_player_no);

        itemView.setOnClickListener(this);
    }

    public void handleData(AudioInfo info, int position) {
        tv_name.setText(info.getAudioTitle());
        tv_time.setText(info.getPlayTime() + "");

        curPosition = position;

        if (info.getPlayTime() == 0) {
            tv_time.setVisibility(View.INVISIBLE);
        } else {
            tv_time.setVisibility(View.VISIBLE);
        }

        if (info.getAudioUrl().equals(mAudioPlayer.getCurrentUri())) {
            iv_status.setImageResource(R.mipmap.tasker_ic_player_list_doing);
            iv_cd.setVisibility(View.VISIBLE);
            tv_no.setVisibility(View.INVISIBLE);
            tv_name.setTextColor(mContext.getResources().getColor(R.color.primary));
        } else {
            iv_status.setImageResource(R.mipmap.tasker_ic_player_list_undo);
            iv_cd.setVisibility(View.INVISIBLE);
            tv_no.setVisibility(View.VISIBLE);
            tv_name.setTextColor(mContext.getResources().getColor(R.color.tasker_txt_black1));
        }
    }

    public static PlayerListVH getInstance(ViewGroup parent, AudioPlayer audioPlayer, SingleItemClickListener singleItemClickListener) {
        return new PlayerListVH(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.tasker_item_player_list, parent, false), audioPlayer, singleItemClickListener);
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener != null) {
            mItemClickListener.onItemOnClick(v, curPosition);
        }
    }
}
