package com.capsule.sample.func.click;

import com.capsule.trunk.Adapter;
import com.capsule.trunk.ViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;

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
