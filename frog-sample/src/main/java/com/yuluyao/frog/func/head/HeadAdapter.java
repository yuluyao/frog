package com.yuluyao.frog.func.head;

import yuluyao.frog.FrogAdapter;
import yuluyao.frog.FrogHolder;

import com.yuluyao.frog.R;
import com.yuluyao.frog.repo.Data;

/**
 * Created by wusheng on 2017/9/2.
 */

public class HeadAdapter extends FrogAdapter<Data, FrogHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_vertical);
  }

  @Override protected void convert(FrogHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent());
  }
}
