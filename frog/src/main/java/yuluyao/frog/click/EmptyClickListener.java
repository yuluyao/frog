package yuluyao.frog.click;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by wusheng on 2017/9/2.
 */

public abstract class EmptyClickListener extends RecyclerView.SimpleOnItemTouchListener {

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

  public abstract void onEmptyClick();

  private class GestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override public boolean onSingleTapUp(MotionEvent e) {
      if (recyclerView.getChildCount() == 0) {
        onEmptyClick();
        return true;
      }
      return false;
    }
  }
}
