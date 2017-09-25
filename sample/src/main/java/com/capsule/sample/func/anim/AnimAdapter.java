package com.capsule.sample.func.anim;

import com.capsule.trunk.Adapter;
import com.capsule.trunk.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/7 14:18
 */
public class AnimAdapter extends Adapter<Data, ViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_common);
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent());
  }
}
