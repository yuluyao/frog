package com.capsule.chick.func.head;

import capsule.chick.ChickAdapter;
import capsule.chick.ChickViewHolder;
import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * Created by wusheng on 2017/9/2.
 */

public class HeadAdapter extends ChickAdapter<Data, ChickViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(ChickViewHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent());
  }
}
