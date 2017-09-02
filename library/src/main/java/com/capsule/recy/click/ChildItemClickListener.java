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
    super();
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
      ViewGroup itemView = (ViewGroup) recyclerView.findChildViewUnder(e.getX(), e.getY());
      if (itemView == null) {
        return false;
      }
      CapViewHolder holder = (CapViewHolder) recyclerView.getChildViewHolder(itemView);
      int position = recyclerView.getChildLayoutPosition(itemView);

      float x_offset = itemView.getX();
      float y_offset = itemView.getY();
      View child = findChildViewUnder(itemView, e.getX() - x_offset, e.getY() - y_offset, holder);
      if (child != null) {
        onChildItemClick(holder, position, child);
        return true;
      } else {
        onItemClick(holder, position);
        return false;
      }
    }

    private View findChildViewUnder(ViewGroup itemView, float x, float y, CapViewHolder holder) {
      for (int i = 0; i < itemView.getChildCount(); i++) {
        View child = itemView.getChildAt(i);
        if ((child instanceof ViewGroup)) {
          float x_offset = child.getX();
          float y_offset = child.getY();
          return findChildViewUnder((ViewGroup) child, x - x_offset, y - y_offset, holder);
        } else {
          final float translationX = ViewCompat.getTranslationX(child);
          final float translationY = ViewCompat.getTranslationY(child);
          float left = child.getLeft() + translationX;
          float right = child.getRight() + translationX;
          float top = child.getTop() + translationY;
          float bottom = child.getBottom() + translationY;
          if (x >= left && x <= right && y >= top && y <= bottom) {
            if (holder.getClickableId().contains(child.getId())) {
              return child;
            }
          }
        }
      }
      return null;
    }
  }
}
