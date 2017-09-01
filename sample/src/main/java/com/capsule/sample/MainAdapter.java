package com.capsule.sample;

import android.widget.TextView;
import com.capsule.recy.CapAdapter;
import com.capsule.recy.CapViewHolder;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/1 14:39
 */
public class MainAdapter extends CapAdapter<String,CapViewHolder> {

  public MainAdapter() {
    setItemLayout(R.layout.item_main);
  }

  @Override protected void convert(CapViewHolder holder, String item) {
    ((TextView) holder.itemView.findViewById(R.id.tv)).setText(item);
  }
}
