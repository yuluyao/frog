package com.capsule.sample.func.divider;

import com.capsule.recy.Adapter;
import com.capsule.recy.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:52
 */
public class DividerGridAdapter extends Adapter<Data, ViewHolder> {

  @Override protected int onGetItemLayoutId() {
    return R.layout.item_data_grid;
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes());
  }
}
