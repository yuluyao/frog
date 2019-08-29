package yuluyao.frog.touch

import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

abstract class BaseTouchListener : RecyclerView.SimpleOnItemTouchListener() {

  protected abstract val gestureListener: GestureDetector.SimpleOnGestureListener
  protected var recyclerView: RecyclerView? = null
  private var detector: GestureDetectorCompat? = null

  override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
    if (recyclerView == null || detector == null) {
      recyclerView = rv
      detector = GestureDetectorCompat(rv.context, gestureListener)
    }
    return detector!!.onTouchEvent(e)
  }

  override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    super.onTouchEvent(rv, e)
  }

  override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    super.onRequestDisallowInterceptTouchEvent(disallowIntercept)
  }


  internal fun getTransformedMotionEvent(event: MotionEvent, child: View): MotionEvent? {
    recyclerView ?: return null
    val offsetX = recyclerView!!.scrollX - child.left
    val offsetY = recyclerView!!.scrollY - child.top
    val transformedEvent = MotionEvent.obtain(event)
    transformedEvent.offsetLocation(offsetX.toFloat(), offsetY.toFloat())
    return transformedEvent
  }


}