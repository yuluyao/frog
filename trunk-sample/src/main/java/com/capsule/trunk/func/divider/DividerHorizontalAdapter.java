package com.capsule.trunk.func.divider;

import capsule.trunk.Adapter;
import capsule.trunk.ViewHolder;
import com.capsule.trunk.R;
import com.capsule.trunk.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:35
 */
public class DividerHorizontalAdapter extends Adapter<Data,ViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_horizontal);
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setImageResource(R.id.icon, item.getIconRes());

  }
}
