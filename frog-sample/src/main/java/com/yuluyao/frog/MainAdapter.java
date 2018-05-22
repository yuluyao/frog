package com.yuluyao.frog;

import yuluyao.frog.FrogAdapter;
import yuluyao.frog.FrogHolder;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/1 14:39
 */
public class MainAdapter extends FrogAdapter<String,FrogHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_main);
  }

  @Override protected void convert(FrogHolder holder, String item) {
    holder.setText(R.id.tv, item);
  }
}
