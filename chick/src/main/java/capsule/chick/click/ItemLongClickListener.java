package capsule.chick.click;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import capsule.chick.ViewHolder;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 17:24
 */
public abstract class ItemLongClickListener extends RecyclerView.SimpleOnItemTouchListener {

  private RecyclerView          recyclerView;
  private GestureDetectorCompat mGestureDetector;

  @Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    mGestureDetector.onTouchEvent(e);
  }

  @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
    if (recyclerView == null) {
      recyclerView = rv;
      mGestureDetector =
          new GestureDetectorCompat(recyclerView.getContext(), new GestureListener());
    }
    //mGestureDetector.onTouchEvent(e);
    return mGestureDetector.onTouchEvent(e);
  }

  @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

  }

  public abstract void onItemLongClick(RecyclerView.ViewHolder vh, int position, View childView);

  private class GestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override public void onLongPress(MotionEvent e) {
      ViewGroup itemView = (ViewGroup) recyclerView.findChildViewUnder(e.getX(), e.getY());
      if (itemView == null) {
        return;
      }
      ViewHolder holder = (ViewHolder) recyclerView.getChildViewHolder(itemView);
      int position = recyclerView.getChildLayoutPosition(itemView);

      View child = findChildViewUnder(holder, itemView, e.getX(), e.getY());
      //if (child != null) {
      //  onItemLongClick(holder, position, child);
      //} else {
      //  onItemLongClick(holder, position);
      //}
      onItemLongClick(holder, position, child);
    }

    private View findChildViewUnder(ViewHolder holder, ViewGroup itemView, float x, float y) {
      for (int i = 0; i < itemView.getChildCount(); i++) {
        float topOffset = itemView.getTop() + ViewCompat.getTranslationY(itemView);

        View child = itemView.getChildAt(i);
        final float translationX = ViewCompat.getTranslationX(child);
        final float translationY = ViewCompat.getTranslationY(child);

        if (x >= child.getLeft() + translationX
            && x <= child.getRight() + translationX
            && y >= child.getTop() + translationY + topOffset
            && y <= child.getBottom() + translationY + topOffset) {
          if (holder.getClickableId().contains(child.getId())) {
            return child;
          }
        }
      }
      return null;
    }
  }
}
