package com.capsule.sample.func.foot;

import com.capsule.recy.Adapter;
import com.capsule.recy.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:29
 */
public class FootAdapter extends Adapter<Data,ViewHolder> {


  @Override protected int onGetItemLayoutId() {
    return R.layout.item_data_vertical;
  }

  @Override protected void convert(ViewHolder holder, Data item) {

  }
}
