package yuluyao.frog.anim.impl;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import yuluyao.frog.anim.BaseItemAnimator;

public class SlideInUpAnimator extends BaseItemAnimator {

  @Override protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .translationY(holder.itemView.getHeight())
        .alpha(0)
        .setDuration(getRemoveDuration())
        .setListener(new DefaultRemoveVpaListener(holder))
        .setStartDelay(getRemoveDelay(holder))
        .start();
  }

  @Override protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setTranslationY(holder.itemView, holder.itemView.getHeight());
    ViewCompat.setAlpha(holder.itemView, 0);
  }

  @Override protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .translationY(0)
        .alpha(1)
        .setDuration(getAddDuration())
        .setListener(new DefaultAddVpaListener(holder))
        .setStartDelay(getAddDelay(holder))
        .start();
  }
}
