package com.yuluyao.frog.func.foot;

import yuluyao.frog.FrogAdapter;
import yuluyao.frog.FrogHolder;

import com.yuluyao.frog.R;
import com.yuluyao.frog.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:29
 */
public class FootAdapter extends FrogAdapter<Data,FrogHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(FrogHolder holder, Data item) {

  }
}
