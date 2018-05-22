package com.yuluyao.frog.func.expand;

import android.view.View;
import yuluyao.frog.FrogAdapter;
import yuluyao.frog.FrogHolder;

import com.yuluyao.frog.R;

/**
 * Created by wusheng on 2017/9/9.
 */

public class ExpandAdapter extends FrogAdapter<ArtBean,FrogHolder> {


  @Override protected void onSetItemLayout() {
    setItemLayout(R.layout.item_expand);
  }

  @Override protected void convert(FrogHolder holder, ArtBean item) {
    holder.setImageResource(R.id.icon, item.getIconRes())
        .setText(R.id.title, item.getTitle())
        .setText(R.id.content, item.getContent())
        .setVisibility(R.id.invi, item.isExpand() ? View.VISIBLE : View.GONE);


  }
}
