package yuluyao.frog.click

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent

abstract class BaseTouchListener : RecyclerView.SimpleOnItemTouchListener() {

  protected abstract val gestureListener: GestureDetector.SimpleOnGestureListener
  protected var recyclerView: RecyclerView? = null
  private var detector: GestureDetectorCompat? = null

  override fun onInterceptTouchEvent(rv: RecyclerView?, e: MotionEvent?): Boolean {
    if (recyclerView == null || detector == null) {
      recyclerView = rv
      detector = GestureDetectorCompat(rv?.context, gestureListener)
    }
    return detector!!.onTouchEvent(e)
  }


}