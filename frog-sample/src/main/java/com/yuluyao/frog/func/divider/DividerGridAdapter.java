package com.yuluyao.frog.func.divider;

import yuluyao.frog.FrogAdapter;
import yuluyao.frog.FrogHolder;

import com.yuluyao.frog.R;
import com.yuluyao.frog.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:52
 */
public class DividerGridAdapter extends FrogAdapter<Data, FrogHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_grid);
  }

  @Override protected void convert(FrogHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes());
  }
}
