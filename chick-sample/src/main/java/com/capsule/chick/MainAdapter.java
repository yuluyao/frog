package com.capsule.chick;

import capsule.chick.ChickAdapter;
import capsule.chick.ChickViewHolder;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/1 14:39
 */
public class MainAdapter extends ChickAdapter<String,ChickViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_main);
  }

  @Override protected void convert(ChickViewHolder holder, String item) {
    holder.setText(R.id.tv, item);
  }
}
