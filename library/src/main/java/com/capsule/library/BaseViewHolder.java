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

  public BaseViewHolder setText(int id,String text){
    ((TextView) itemView.findViewById(id)).setText(text);
    return this;
  }

  public BaseViewHolder setVisibility(int id ,boolean visible ){
    itemView.findViewById(id).setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    return this;
  }


}
