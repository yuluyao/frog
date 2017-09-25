package com.capsule.trunk.anim.impl;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import com.capsule.trunk.anim.BaseItemAnimator;

public class ScaleInLeftAnimator extends BaseItemAnimator {

  @Override protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setPivotX(holder.itemView, 0);
  }

  @Override protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .scaleX(0)
        .scaleY(0)
        .setDuration(getRemoveDuration())
        .setListener(new DefaultRemoveVpaListener(holder))
        .setStartDelay(getRemoveDelay(holder))
        .start();
  }

  @Override protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setPivotX(holder.itemView, 0);
    ViewCompat.setScaleX(holder.itemView, 0);
    ViewCompat.setScaleY(holder.itemView, 0);
  }

  @Override protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .scaleX(1)
        .scaleY(1)
        .setDuration(getAddDuration())
        .setListener(new DefaultAddVpaListener(holder))
        .setStartDelay(getAddDelay(holder))
        .start();
  }
}
