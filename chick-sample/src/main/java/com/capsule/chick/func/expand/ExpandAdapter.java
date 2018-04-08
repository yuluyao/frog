package com.capsule.chick.func.expand;

import android.view.View;
import capsule.chick.ChickAdapter;
import capsule.chick.ChickViewHolder;

import com.capsule.chick.R;

/**
 * Created by wusheng on 2017/9/9.
 */

public class ExpandAdapter extends ChickAdapter<ArtBean,ChickViewHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_expand);
  }

  @Override protected void convert(ChickViewHolder holder, ArtBean item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setVisibility(R.id.invi, item.isExpand() ? View.VISIBLE : View.GONE);


  }
}
