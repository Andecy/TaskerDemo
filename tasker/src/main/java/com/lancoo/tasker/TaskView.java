package com.lancoo.tasker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lancoo.tasker.adapter.SingleItemClickListener;
import com.lancoo.tasker.audio.AudioPlayListener;
import com.lancoo.tasker.audio.AudioPlayer;
import com.lancoo.tasker.audio.PlayerListAdapter;
import com.lancoo.tasker.content.timu.IAudioInfo;
import com.lancoo.tasker.content.timu.ITaskTimu;
import com.lancoo.tasker.content.timu.ITopicTimu;
import com.lancoo.tasker.item.BaseItemAdapter;
import com.lancoo.tasker.timulist.ItemPopupWindow;
import com.lancoo.tasker.timulist.TopicPopupWindow;
import com.lancoo.tasker.tool.UITool;
import com.lancoo.tasker.view.SplitView;

import java.util.List;

/**
 * Author: Andecy
 * Time: 2017/3/22
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class TaskView extends LinearLayout implements AudioPlayListener, View.OnClickListener {

    private static final String TAG = "TaskView";

    //header of task
    private TextView tv_score;
    private TextView tv_name;
    private TextView tv_count;
    private TextView tv_topicno;

    //player
    private RelativeLayout rl_player;
    private TextView tv_player_time;
    private TextView tv_player_title;
    private SeekBar sb_player;
    private ImageView iv_player_start;
    private ImageView iv_player_list;
    private AudioPlayer mAudioPlayer;

    //player list
    private TextView tv_player_count;
    private RecyclerView rv_player_list;
    private PlayerListAdapter mPlayerListAdapter;
    private BottomSheetDialog mPlayerListDialog;
    private List<? extends IAudioInfo> mAudioInfos;

    //题目List
    private ItemPopupWindow mItemPopupWindow;
    private TopicPopupWindow mTopicPopupWindow;
    private SingleItemClickListener topicListener = new SingleItemClickListener() {
        @Override
        public void onItemOnClick(View view, int postion) {
            changeTopicPosition(postion);
            mTopicPopupWindow.dismiss();
        }
    };

    private SingleItemClickListener itemListener = new SingleItemClickListener() {
        @Override
        public void onItemOnClick(View view, int postion) {
            changeItemPosition(postion);
            mItemPopupWindow.dismiss();
        }
    };

    //大题内容
    private TextView tv_content;

    //分页
    private SplitView mSplitView;

    //小题
    private ViewPager vp_item;
    private TextView tv_type;
    private TextView tv_itemNo;
    private TextView tv_itemCount;
    private ImageView iv_switcher;

    private TaskListener mTaskListener;

    private ItemSwitchListener mSwitchListener;

    private BaseItemAdapter mItemAdapter;

    private int curTopicPosition;
    private int curItemPosition;


    private ViewPager.OnPageChangeListener itemChangeListener;

    public TaskView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.tasker_content, this);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TaskView);
        a.recycle();

        //header of task
        tv_score = findView(R.id.tv_tasker_score);
        tv_name = findView(R.id.tv_tasker_name);
        tv_count = findView(R.id.tv_tasker_count);
        tv_topicno = findView(R.id.tv_tasker_topicno);

        //player
        rl_player = findView(R.id.rl_module_player);
        tv_player_time = findView(R.id.tv_player_time);
        tv_player_title = findView(R.id.tv_player_title);
        sb_player = findView(R.id.sb_player_progress);
        iv_player_start = findView(R.id.iv_player_start);
        iv_player_list = findView(R.id.iv_player_list);
        iv_player_start.setOnClickListener(this);
        iv_player_list.setOnClickListener(this);


        //大题内容
        tv_content = findView(R.id.tv_tasker_topic_content);

        //分页
        mSplitView = findView(R.id.split_tasker);

        //小题
        vp_item = findView(R.id.vp_tasker_item);
        tv_type = findView(R.id.tv_tasker_topic_type);
        tv_itemNo = findView(R.id.tv_tasker_topic_item_no);
        tv_itemCount = findView(R.id.tv_tasker_topic_item_count);
        iv_switcher = findView(R.id.iv_tasker_topic_switcher);

        iv_switcher.setOnClickListener(this);
    }

    private <T extends View> T findView(@IdRes int viewId) {
        return (T) findViewById(viewId);
    }


    private void injectData(ITaskTimu taskTimu) {
        setTaskInfo(taskTimu);
        setTopicInfo(0);
    }

    /**
     * 设置作业信息
     *
     * @param taskTimu 作业信息类
     */
    private void setTaskInfo(ITaskTimu taskTimu) {
//        tv_score.setText();
        tv_name.setText(taskTimu.getTaskName());
        if (taskTimu.getTopicTimus() == null) {
            return;
        }
        tv_count.setText("/" + taskTimu.getTopicTimus().size());
    }

    /**
     * 设置整份作业的Title
     *
     * @param titleName 标题名称
     */
    public void setTaskTitleName(String titleName) {
        tv_score.setText(titleName);
        invalidate();
    }

    /**
     * 设置大题信息
     *
     * @param position 大题位置
     */
    private void setTopicInfo(int position) {
        if (mItemAdapter == null ||
                mItemAdapter.getTaskTimu().getTopicTimus().size() <= position) {
            return;
        }

        curTopicPosition = position;

        setItemInfo(0);

        ITopicTimu topicTimu = mItemAdapter.getTaskTimu().getTopicTimus().get(position);

        if (mTaskListener != null) {
            mTaskListener.onTimuChanged(curTopicPosition, curItemPosition);
        }

        if (vp_item.getAdapter() != mItemAdapter) {
            vp_item.setAdapter(mItemAdapter);
        } else {
            //update adapter data
            mItemAdapter.update(curTopicPosition);
        }
        tv_topicno.setText("" + (curTopicPosition + 1));

        tv_itemCount.setText("/(" + topicTimu.getItemTimus().size() + ")");
        tv_type.setText("(大题总分：" + topicTimu.getScore() + "分)" + topicTimu.getTypeName());
        if (TextUtils.isEmpty(topicTimu.getContent()) &&
                (topicTimu.getAudioInfos() == null || topicTimu.getAudioInfos().isEmpty())) {
            mSplitView.setSplitRatio(0);
        } else {
            mSplitView.setSplitRatio(0.3f);
            UITool.setRichTitle(topicTimu.getContent(), tv_content);
        }

        //---player--------
        mAudioInfos = topicTimu.getAudioInfos();
        if (topicTimu.getAudioInfos() == null || topicTimu.getAudioInfos().isEmpty()) {
            rl_player.setVisibility(GONE);
        } else {
            rl_player.setVisibility(VISIBLE);
            setPlayer(topicTimu.getAudioInfos().get(0), false);

        }
    }


    private void setItemInfo(int position) {
        curItemPosition = position;

        vp_item.setCurrentItem(curItemPosition);
        tv_itemNo.setText("(" + (curItemPosition + 1) + ")");

        if (itemChangeListener == null) {
            itemChangeListener = new ViewPager.SimpleOnPageChangeListener() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    curItemPosition = position;
                    tv_itemNo.setText("(" + (position + 1) + ")");
                    if (mTaskListener != null) {
                        mTaskListener.onTimuChanged(curTopicPosition, curItemPosition);
                    }
                }
            };
            vp_item.addOnPageChangeListener(itemChangeListener);
        }

    }

    /**
     * 设置一个用于监听作业事件的Listener.
     *
     * @param listener 要添加的监听器.
     */
    public void setTaskListener(@NonNull TaskListener listener) {
        mTaskListener = listener;
    }

    public void setOnItemSwitchClickListener(@NonNull ItemSwitchListener listener) {
        iv_switcher.setVisibility(VISIBLE);
        mSwitchListener = listener;
    }

    public void setItemAdapter(@NonNull BaseItemAdapter adapter) {
        mItemAdapter = adapter;
        injectData(mItemAdapter.getTaskTimu());
        invalidate();
    }

    public void changeTopicPosition(int position) {
        if (position < 0 || position == curTopicPosition) {
            return;
        }
        setTopicInfo(position);
        invalidate();
    }

    public void changeItemPosition(int position) {
        if (position < 0 || position == curItemPosition) {
            return;
        }
        setItemInfo(position);
        invalidate();
    }

    //===============================听力=====================

    public AudioPlayer getAudioPlayer() {
        return mAudioPlayer;
    }

    private void setPlayer(IAudioInfo info, boolean isAutoPlayer) {
        if (mAudioPlayer == null) {
            mAudioPlayer = new AudioPlayer();
            mAudioPlayer.setAudioPlayListener(this);
            mAudioPlayer.bindSeekBar(sb_player);
            mAudioPlayer.bindTimer(tv_player_time);
        }
        mAudioPlayer.setAutoPlay(isAutoPlayer);
        tv_player_title.setText(info.getAudioTitle());
        mAudioPlayer.loadUri(info.getAudioUrl());
    }

    @Override
    public void onAudioPlayPrepare() {
    }

    @Override
    public void onAudioPlayPrepared(MediaPlayer mp) {
        sb_player.setEnabled(true);
    }

    @Override
    public void onAudioPlayBufferingUpdate(MediaPlayer mp, int percent) {

    }

    @Override
    public void onAudioPlayUpdate(MediaPlayer mp) {

    }

    @Override
    public void onAudioPlayStart() {
        iv_player_start.setImageResource(R.mipmap.tasker_ic_player_pause2);
    }

    @Override
    public void onAudioPlayPause() {
        iv_player_start.setImageResource(R.mipmap.tasker_ic_player_start2);

    }

    @Override
    public void onAudioPlayStop() {
    }

    @Override
    public void onAudioPlayCompletion() {
        iv_player_start.setImageResource(R.mipmap.tasker_ic_player_start2);
    }

    @Override
    public void onAudioPlayError(MediaPlayer mp, int what, int extra) {
        iv_player_start.setImageResource(R.mipmap.tasker_ic_player_start2);
        if (mTaskListener != null) {
            mTaskListener.onAudioPlayError(mp, what, extra);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.iv_player_start) {
            if (mAudioPlayer == null || !mAudioPlayer.isPrepared()) {
                return;
            }
            mAudioPlayer.pauseOrStart();
        } else if (id == R.id.iv_player_list) {
            if (mPlayerListDialog == null) {
                mPlayerListDialog = new BottomSheetDialog(getContext());
                mPlayerListDialog.setContentView(R.layout.tasker_list_player);
                rv_player_list = (RecyclerView) mPlayerListDialog.findViewById(R.id.rv_player_list);
                tv_player_count = (TextView) mPlayerListDialog.findViewById(R.id.tv_player_count);

                rv_player_list.setLayoutManager(new LinearLayoutManager(getContext()));
                rv_player_list.setItemAnimator(new DefaultItemAnimator());
                mPlayerListAdapter = new PlayerListAdapter(mAudioInfos, mAudioPlayer);
                rv_player_list.setAdapter(mPlayerListAdapter);
                mPlayerListAdapter.setItemClickListener(new SingleItemClickListener() {
                    @Override
                    public void onItemOnClick(View view, int postion) {
                        setPlayer(mAudioInfos.get(postion), true);
                        mPlayerListDialog.dismiss();
                    }
                });
            }
            mPlayerListAdapter.setData(mAudioInfos);
            tv_player_count.setText("音频列表(" + mAudioInfos.size() + ")");
            mPlayerListAdapter.notifyDataSetChanged();
            mPlayerListDialog.show();

            mPlayerListDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
        } else if (id == R.id.iv_tasker_topic_switcher && mSwitchListener != null) {
            mSwitchListener.onItemSwitcherClick(v);
        }
    }

    /**
     * 展示小题列表选择页面
     */
    public void showItemSelectView() {
        mItemPopupWindow = new ItemPopupWindow(getContext(), curItemPosition,
                mItemAdapter.getTaskAnswer().geTopicAnswers().get(curTopicPosition).getItemAnswers(),
                itemListener);
        mItemPopupWindow.showAtLocation(this, Gravity.CENTER, 0, 0);
    }

    /**
     * 展示大题列表选择页面
     */
    public void showTopicSelectView() {
        mTopicPopupWindow = new TopicPopupWindow(getContext(),
                mItemAdapter.getTaskTimu().getTopicTimus(),
                mItemAdapter.getTaskAnswer().geTopicAnswers(),
                curTopicPosition,
                topicListener, mTaskListener);
        mTopicPopupWindow.showAtLocation(this, Gravity.CENTER, 0, 0);
    }

    public void dismissTopicSelectView() {
        if (mTopicPopupWindow != null) {
            mTopicPopupWindow.dismiss();
        }
    }

    public interface TaskListener {

        void onTimuChanged(int topicPosition, int itemPosition);

        void onAudioPlayError(MediaPlayer mp, int what, int extra);

        void onTaskSubmitClick();
    }

    public abstract static class SimpleTaskListener implements TaskListener {

        @Override
        public void onAudioPlayError(MediaPlayer mp, int what, int extra) {

        }

        @Override
        public void onTimuChanged(int topicPosition, int itemPosition) {
        }

        @Override
        public void onTaskSubmitClick() {

        }
    }

    public interface ItemSwitchListener {

        void onItemSwitcherClick(View view);
    }
}
