package com.yuluyao.frog.func.anim;

import yuluyao.frog.FrogAdapter;
import yuluyao.frog.FrogHolder;

import com.yuluyao.frog.R;
import com.yuluyao.frog.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/7 14:18
 */
public class AnimAdapter extends FrogAdapter<Data, FrogHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_common);
  }

  @Override protected void convert(FrogHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent());
  }
}
