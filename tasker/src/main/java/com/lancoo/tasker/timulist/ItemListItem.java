package com.lancoo.tasker.timulist;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.Utils;
import com.lancoo.tasker.R;
import com.lancoo.tasker.adapter.BaseRecylerItem;
import com.lancoo.tasker.content.answer.IItemAnswer;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Author: Andecy
 * Time: 2017/5/3
 * Email: andecy@foxmail.com
 * Description: TODO
 */

public class ItemListItem implements BaseRecylerItem<IItemAnswer> {
    private int curPosition;

    private TextView tv_no;
    private CircleImageView civ_no;
    private ImageView iv_indicator;


    public ItemListItem(int curPosition) {
        this.curPosition = curPosition;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.tasker_item_number_item;
    }

    @Override
    public void bindViews(View view) {
        tv_no = (TextView) view.findViewById(R.id.tv_item_number_no);
        civ_no = (CircleImageView) view.findViewById(R.id.civ_item_number_no);
        iv_indicator = (ImageView) view.findViewById(R.id.iv_item_number_indicator);
    }

    @Override
    public void setViews() {

    }

    @Override
    public void handleData(IItemAnswer data, int position) {
        tv_no.setText("" + (position + 1));

        if (!TextUtils.isEmpty(data.getAnswer())) {
            civ_no.setImageResource(android.R.color.transparent);
            tv_no.setTextColor(Color.WHITE);
        } else {
            civ_no.setImageResource(R.color.default_background);
            tv_no.setTextColor(Utils.getContext().getResources().getColor(R.color.accent));
        }

        iv_indicator.setVisibility(curPosition == position ? View.VISIBLE : View.INVISIBLE);
    }
}
