package yuluyao.frog.anim.impl;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import yuluyao.frog.anim.BaseItemAnimator;

public class LandingAnimator extends BaseItemAnimator {

  @Override protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .alpha(0)
        .scaleX(1.5f)
        .scaleY(1.5f)
        .setDuration(getRemoveDuration())
        .setListener(new DefaultRemoveVpaListener(holder))
        .setStartDelay(getRemoveDelay(holder))
        .start();
  }

  @Override protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setAlpha(holder.itemView, 0);
    ViewCompat.setScaleX(holder.itemView, 1.5f);
    ViewCompat.setScaleY(holder.itemView, 1.5f);
  }

  @Override protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .alpha(1)
        .scaleX(1)
        .scaleY(1)
        .setDuration(getAddDuration())
        .setListener(new DefaultAddVpaListener(holder))
        .setStartDelay(getAddDelay(holder))
        .start();
  }
}
