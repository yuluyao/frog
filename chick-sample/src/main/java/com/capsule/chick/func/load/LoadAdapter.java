package com.capsule.chick.func.load;

import capsule.chick.ChickAdapter;
import capsule.chick.ChickViewHolder;
import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 21:44
 */
public class LoadAdapter extends ChickAdapter<Data, ChickViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ChickViewHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setClickableId(R.id.icon);
  }
}
