package com.capsule.library;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 14:49
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {


  public BaseViewHolder(View itemView) {
    super(itemView);
  }

  public BaseViewHolder setText(int viewId,String text){
    ((TextView) itemView.findViewById(viewId)).setText(text);
    return this;
  }
}
