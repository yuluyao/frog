package yuluyao.frog.touch

import android.graphics.Rect
import android.graphics.Region
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

abstract class BaseTouchListener : RecyclerView.SimpleOnItemTouchListener() {

  internal abstract val gestureListener: GestureDetector.SimpleOnGestureListener
  protected var recyclerView: RecyclerView? = null
  private var detector: GestureDetectorCompat? = null

  override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
    if (recyclerView == null || detector == null) {
      recyclerView = rv
      detector = GestureDetectorCompat(rv.context, gestureListener)
    }
    return detector!!.onTouchEvent(e)
  }

//  override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//    super.onTouchEvent(rv, e)
//  }

//  override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//    super.onRequestDisallowInterceptTouchEvent(disallowIntercept)
//  }


  internal fun getTransformedMotionEvent(event: MotionEvent, child: View): MotionEvent? {
    recyclerView ?: return null
    val offsetX = recyclerView!!.scrollX - child.left
    val offsetY = recyclerView!!.scrollY - child.top
    val transformedEvent = MotionEvent.obtain(event)
    transformedEvent.offsetLocation(offsetX.toFloat(), offsetY.toFloat())
    return transformedEvent
  }


  //<editor-fold desc="子View手势处理">
  abstract val listenedChildrenIds: IntArray
  internal var target: View? = null
  internal fun findTarget(parent: ViewGroup, rawX: Int, rawY: Int): View? {
    listenedChildrenIds ?: return target
    if (listenedChildrenIds!!.contains(parent.id)) {
      if (isClickInside(parent, rawX, rawY)) {
        target = parent
      }
    }
    for (i in 0 until parent.childCount) {
      val childAti = parent.getChildAt(i)
      if (childAti is ViewGroup) {
        target = findTarget(childAti, rawX, rawY)
      } else {
        if (!listenedChildrenIds!!.contains(childAti.id)) continue
        if (isClickInside(childAti, rawX, rawY)) {
          target = childAti
        }
      }
    }
    return target
  }

  internal fun isClickInside(view: View, rawX: Int, rawY: Int): Boolean {
    val r = Rect()
    view.getGlobalVisibleRect(r)
    val region = Region(r)
    return region.contains(rawX, rawY)
  }
  //</editor-fold>


}