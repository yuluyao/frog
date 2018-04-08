package com.capsule.chick.func.foot;

import capsule.chick.ChickAdapter;
import capsule.chick.ChickViewHolder;

import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:29
 */
public class FootAdapter extends ChickAdapter<Data,ChickViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ChickViewHolder holder, Data item) {

  }
}
