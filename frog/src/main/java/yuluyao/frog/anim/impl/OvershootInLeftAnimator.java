package yuluyao.frog.anim.impl;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.animation.OvershootInterpolator;
import yuluyao.frog.anim.BaseItemAnimator;

public class OvershootInLeftAnimator extends BaseItemAnimator {

  private final float mTension;

  public OvershootInLeftAnimator() {
    mTension = 2.0f;
  }

  public OvershootInLeftAnimator(float mTension) {
    this.mTension = mTension;
  }

  @Override protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .translationX(-holder.itemView.getRootView().getWidth())
        .setDuration(getRemoveDuration())
        .setListener(new DefaultRemoveVpaListener(holder))
        .setStartDelay(getRemoveDelay(holder))
        .start();
  }

  @Override protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setTranslationX(holder.itemView, -holder.itemView.getRootView().getWidth());
  }

  @Override protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .translationX(0)
        .setDuration(getAddDuration())
        .setListener(new DefaultAddVpaListener(holder))
        .setInterpolator(new OvershootInterpolator(mTension))
        .setStartDelay(getAddDelay(holder))
        .start();
  }
}

