package com.capsule.sample.func.empty;

import com.capsule.trunk.Adapter;
import com.capsule.trunk.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

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
