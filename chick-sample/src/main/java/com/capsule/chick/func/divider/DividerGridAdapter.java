package com.capsule.chick.func.divider;

import capsule.chick.Adapter;
import capsule.chick.ViewHolder;
import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:52
 */
public class DividerGridAdapter extends Adapter<Data, ViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_grid);
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes());
  }
}
