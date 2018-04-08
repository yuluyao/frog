package com.capsule.chick.func.empty;

import capsule.chick.ChickAdapter;
import capsule.chick.ChickViewHolder;

import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * Created by wusheng on 2017/9/2.
 */

public class EmptyAdapter extends ChickAdapter<Data, ChickViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ChickViewHolder holder, Data item) {

  }
}
