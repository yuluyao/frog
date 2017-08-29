package com.capsule.library;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 17:24
 */
public abstract class TouchToClick implements RecyclerView.OnItemTouchListener {

  private GestureDetectorCompat mGestureDetector;
  private RecyclerView          mRecyclerView;

  public TouchToClick(RecyclerView recyclerView) {
    mRecyclerView = recyclerView;
    mGestureDetector =
        new GestureDetectorCompat(mRecyclerView.getContext(), new BaseGestureListener());
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

  public abstract void onItemClick(RecyclerView.ViewHolder vh, Object item);

  public abstract void onItemLongClick(RecyclerView.ViewHolder vh, Object item);

  private class BaseGestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override public boolean onSingleTapUp(MotionEvent e) {
      View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
      int position = mRecyclerView.getChildLayoutPosition(child);
      BaseAdapter adapter = (BaseAdapter) mRecyclerView.getAdapter();
      Object item = adapter.getData(position);
      if (child != null && item != null) {
        RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(child);
        onItemClick(holder, item);
      }
      return true;
    }

    @Override public void onLongPress(MotionEvent e) {
      View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
      int position = mRecyclerView.getChildLayoutPosition(child);
      BaseAdapter adapter = (BaseAdapter) mRecyclerView.getAdapter();
      Object item = adapter.getData(position);
      if (child != null && item != null) {
        RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(child);
        onItemLongClick(holder, item);
      }
    }
  }
}
