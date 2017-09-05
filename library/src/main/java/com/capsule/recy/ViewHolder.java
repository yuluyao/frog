package com.capsule.recy;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 14:49
 */
public class ViewHolder extends RecyclerView.ViewHolder {

  private List<Integer> clickableId = new ArrayList<>();

  public ViewHolder(View itemView) {
    super(itemView);
  }

  public void setClickableId(int... id) {
    for (int i : id) {
      if (!clickableId.contains(i)) {
        clickableId.add(i);
      }
    }
  }

  public List<Integer> getClickableId() {
    return clickableId;
  }

  public ViewHolder setText(int id, String text) {
    ((TextView) itemView.findViewById(id)).setText(text);
    return this;
  }

  public ViewHolder setVisibility(int id, boolean visible) {
    itemView.findViewById(id).setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    return this;
  }
}
