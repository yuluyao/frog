package com.capsule.recy.click;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.capsule.recy.CapViewHolder;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 17:24
 */
public abstract class ChildItemClickListener extends ItemClickListener {

  private GestureDetectorCompat mGestureDetector;

  public ChildItemClickListener(RecyclerView recyclerView) {
    super(recyclerView);
    mGestureDetector =
        new GestureDetectorCompat(recyclerView.getContext(), new ChildClickListener());
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

  public abstract void onChildItemClick(RecyclerView.ViewHolder vh, int position, View childView);

  private class ChildClickListener extends GestureDetector.SimpleOnGestureListener {

    @Override public boolean onSingleTapUp(MotionEvent e) {
      ViewGroup itemView = (ViewGroup) mRecyclerView.findChildViewUnder(e.getX(), e.getY());
      if (itemView == null) {
        return false;
      }
      CapViewHolder holder = (CapViewHolder) mRecyclerView.getChildViewHolder(itemView);
      int position = mRecyclerView.getChildLayoutPosition(itemView);

      View child = findChildViewUnder(holder, itemView, e.getX(), e.getY());
      if (child != null) {
        onChildItemClick(holder, position, child);
        return true;
      } else {
        onItemClick(holder, position);
        return false;
      }
    }

    private View findChildViewUnder(CapViewHolder holder, ViewGroup itemView, float x, float y) {
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
