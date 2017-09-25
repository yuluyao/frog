package com.capsule.trunk.anim.impl;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import com.capsule.trunk.anim.BaseItemAnimator;

public class SlideInLeftAnimator extends BaseItemAnimator {

  @Override protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .translationX(-holder.itemView.getRootView().getWidth())
        .setDuration(getRemoveDuration())
        .setListener(new DefaultRemoveVpaListener(holder))
        .setStartDelay(getRemoveDelay(holder))
        .start();
  }

  @Override protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
    super.preAnimateRemoveImpl(holder);
  }

  @Override protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setTranslationX(holder.itemView, -holder.itemView.getRootView().getWidth());
  }

  @Override protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .translationX(0)
        .setDuration(getAddDuration())
        .setListener(new DefaultAddVpaListener(holder))
        .setStartDelay(getAddDelay(holder))
        .start();
  }
}
