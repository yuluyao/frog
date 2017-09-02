//package com.capsule.recy.click;
//
//import android.support.v4.view.GestureDetectorCompat;
//import android.support.v7.widget.RecyclerView;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//
///**
// * 描 述：
// * 作 者：Vegeta Yu
// * 时 间：2017/8/29 17:24
// */
//public abstract class SimpleClickListener implements RecyclerView.OnItemTouchListener {
//
//  protected RecyclerView          recyclerView;
//  private   GestureDetectorCompat mGestureDetector;
//
//  @Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {
//    mGestureDetector.onTouchEvent(e);
//  }
//
//  @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//    if (recyclerView == null) {
//      recyclerView = rv;
//      mGestureDetector = new GestureDetectorCompat(recyclerView.getContext(), new ClickListener());
//    }
//    //mGestureDetector.onTouchEvent(e);
//    return mGestureDetector.onTouchEvent(e);
//  }
//
//  @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//  }
//
//  public abstract void onItemClick(RecyclerView.ViewHolder vh, int position);
//
//  private class ClickListener extends GestureDetector.SimpleOnGestureListener {
//
//    @Override public boolean onSingleTapUp(MotionEvent e) {
//      View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
//      int position = recyclerView.getChildLayoutPosition(child);
//      if (child != null) {
//        RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(child);
//        onItemClick(holder, position);
//      }
//      return true;
//    }
//  }
//}
