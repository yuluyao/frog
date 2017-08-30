package com.capsule.library.click;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.capsule.library.BaseAdapter;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 17:24
 */
public abstract class ItemClickListener implements RecyclerView.OnItemTouchListener {

  protected RecyclerView          mRecyclerView;
  private   GestureDetectorCompat mGestureDetector;

  public ItemClickListener(RecyclerView recyclerView) {
    mRecyclerView = recyclerView;
    mGestureDetector = new GestureDetectorCompat(mRecyclerView.getContext(), new ClickListener());
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

  public abstract void onItemClick(RecyclerView.ViewHolder vh, int position);

  private class ClickListener extends GestureDetector.SimpleOnGestureListener {

    @Override public boolean onSingleTapUp(MotionEvent e) {
      View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
      int position = mRecyclerView.getChildLayoutPosition(child);
      if (child != null) {
        RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(child);
        onItemClick(holder, position);
      }
      return true;
    }
  }
}
