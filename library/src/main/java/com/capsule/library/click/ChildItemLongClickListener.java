package com.capsule.library.click;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.capsule.library.BaseViewHolder;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 17:24
 */
public abstract class ChildItemLongClickListener extends ItemLongClickListener {

  private GestureDetectorCompat mGestureDetector;

  public ChildItemLongClickListener(RecyclerView recyclerView) {
    super(recyclerView);
    mGestureDetector =
        new GestureDetectorCompat(mRecyclerView.getContext(), new ChildLongClickListener());
  }

  @Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    mGestureDetector.onTouchEvent(e);
  }

  @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
    mGestureDetector.onTouchEvent(e);
    return false;
  }

  @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

  }

  public abstract void onChildItemLongClick(RecyclerView.ViewHolder vh, Object item,
      View childView);

  private class ChildLongClickListener extends GestureDetector.SimpleOnGestureListener {

    @Override public void onLongPress(MotionEvent e) {
      ViewGroup itemView = (ViewGroup) mRecyclerView.findChildViewUnder(e.getX(), e.getY());
      if (itemView == null) {
        return;
      }
      BaseViewHolder holder = (BaseViewHolder) mRecyclerView.getChildViewHolder(itemView);
      int position = mRecyclerView.getChildLayoutPosition(itemView);

      View child = findChildViewUnder(holder, itemView, e.getX(), e.getY());
      if (child != null) {
        onChildItemLongClick(holder, position, child);
      } else {
        onItemLongClick(holder, position);
      }
    }

    private View findChildViewUnder(BaseViewHolder holder, ViewGroup itemView, float x, float y) {
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
