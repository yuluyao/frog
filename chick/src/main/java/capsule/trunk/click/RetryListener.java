package capsule.trunk.click;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by wusheng on 2017/9/2.
 */

public abstract class RetryListener extends RecyclerView.SimpleOnItemTouchListener {

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

  public abstract void onRetryLoad();

  private class GestureListener extends GestureDetector.SimpleOnGestureListener {

    @Override public boolean onSingleTapUp(MotionEvent e) {
      View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
      if (view != null) {
        return false;//点击到的是item，就不处理
      }
      int itemCount = recyclerView.getAdapter().getItemCount();
      View itemView = recyclerView.findViewHolderForAdapterPosition(itemCount - 1).itemView;
      //float x_offset = itemView.getX();
      float y_offset = itemView.getY();
      float height = itemView.getHeight();
      if (e.getY() > y_offset + height) {
        onRetryLoad();
        return true;
      }
      return false;
    }
  }
}
