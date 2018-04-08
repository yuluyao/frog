package com.capsule.chick.func.empty;

import capsule.chick.Adapter;
import capsule.chick.ViewHolder;
import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * Created by wusheng on 2017/9/2.
 */

public class EmptyAdapter extends Adapter<Data, ViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ViewHolder holder, Data item) {

  }
}
