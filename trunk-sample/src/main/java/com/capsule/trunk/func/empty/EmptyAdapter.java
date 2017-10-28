package com.capsule.trunk.func.empty;

import capsule.trunk.Adapter;
import capsule.trunk.ViewHolder;
import com.capsule.trunk.R;
import com.capsule.trunk.repo.Data;

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
