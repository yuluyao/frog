package yuluyao.frog.anim.impl;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import yuluyao.frog.anim.BaseItemAnimator;

public class FlipInLeftYAnimator extends BaseItemAnimator {

  @Override protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .rotationY(90)
        .setDuration(getRemoveDuration())
        .setListener(new DefaultRemoveVpaListener(holder))
        .setStartDelay(getRemoveDelay(holder))
        .start();
  }

  @Override protected void preAnimateAddImpl(RecyclerView.ViewHolder holder) {
    ViewCompat.setRotationY(holder.itemView, 90);
  }

  @Override protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
    ViewCompat.animate(holder.itemView)
        .rotationY(0)
        .setDuration(getAddDuration())
        .setListener(new DefaultAddVpaListener(holder))
        .setStartDelay(getAddDelay(holder))
        .start();
  }
}
