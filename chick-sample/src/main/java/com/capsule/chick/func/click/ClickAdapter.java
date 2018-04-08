package com.capsule.chick.func.click;

import capsule.chick.Adapter;
import capsule.chick.ViewHolder;
import com.capsule.chick.R;
import com.capsule.chick.repo.Data;

/**
 * Created by wusheng on 2017/9/2.
 */

public class ClickAdapter extends Adapter<Data, ViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_data_common);
  }

  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setClickableId(R.id.icon);
  }
}
