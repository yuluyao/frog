package capsule.chick.anim.impl;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import capsule.chick.anim.BaseItemAnimator;

public class ScaleInTopAnimator extends BaseItemAnimator {


  @Override protected void preAnimateRemoveImpl(RecyclerView.ViewHolder holder) {
    // @TODO https://code.google.com/p/android/issues/detail?id=80863
    //        ViewCompat.setPivotY(holder.itemView, 0);
    holder.itemView.setPivotY(0);
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
    // @TODO https://code.google.com/p/android/issues/detail?id=80863
    //        ViewCompat.setPivotY(holder.itemView, 0);
    holder.itemView.setPivotY(0);
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
