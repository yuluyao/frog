package com.capsule.sample;

import android.widget.TextView;
import com.capsule.recy.ViewHolder;
import com.capsule.recy.Adapter;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/1 14:39
 */
public class MainAdapter extends Adapter<String,ViewHolder> {

  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_main);
  }

  @Override protected void convert(ViewHolder holder, String item) {
    ((TextView) holder.itemView.findViewById(R.id.tv)).setText(item);
  }
}
