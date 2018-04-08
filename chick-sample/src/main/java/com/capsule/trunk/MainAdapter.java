package com.capsule.trunk;

import capsule.trunk.ViewHolder;
import capsule.trunk.Adapter;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/1 14:39
 */
public class MainAdapter extends Adapter<String,ViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_main);
  }

  @Override protected void convert(ViewHolder holder, String item) {
    holder.setText(R.id.tv, item);
  }
}
