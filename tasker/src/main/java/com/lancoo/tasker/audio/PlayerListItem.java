package com.lancoo.tasker.audio;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lancoo.tasker.R;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.timu.IAudioInfo;

/**
 * Author: Andecy
 * Time: 2017/5/3
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class PlayerListItem implements BaseRecylerItem<IAudioInfo> {
    private Context mContext;

    private TextView tv_name;
    private TextView tv_time;
    private ImageView iv_cd;
    private TextView tv_no;
    private ImageView iv_status;

    private AudioPlayer mAudioPlayer;

    public PlayerListItem(AudioPlayer audioPlayer) {
        mAudioPlayer = audioPlayer;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.tasker_item_player;
    }

    @Override
    public void bindViews(View view) {
        mContext = view.getContext();

        tv_name = (TextView) view.findViewById(R.id.tv_item_player_name);
        tv_time = (TextView) view.findViewById(R.id.tv_item_player_time);
        iv_status = (ImageView) view.findViewById(R.id.iv_item_player_status);
        iv_cd = (ImageView) view.findViewById(R.id.iv_item_player_cd);
        tv_no = (TextView) view.findViewById(R.id.tv_item_player_no);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(IAudioInfo info, int position) {
        tv_name.setText(info.getAudioTitle());
        tv_time.setText(info.getPlayTime() + "");

        if (info.getPlayTime() == 0) {
            tv_time.setVisibility(View.GONE);
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
}
