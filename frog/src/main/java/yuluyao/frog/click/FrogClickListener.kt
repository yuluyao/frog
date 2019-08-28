package yuluyao.frog.click

import android.view.GestureDetector
import android.view.MotionEvent

abstract class FrogClickListener : BaseTouchListener() {

  abstract fun onItemClicked(position: Int)

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = SingleTapUpListener()

  inner class SingleTapUpListener : GestureDetector.SimpleOnGestureListener() {
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      val child = recyclerView?.findChildViewUnder(e.x, e.y)
      child ?: return false
      val position = recyclerView?.getChildAdapterPosition(child)
      position ?: return false

      child.onTouchEvent(getTransformedMotionEvent(e,child))
      onItemClicked(position)
      return true
    }

    override fun onShowPress(e: MotionEvent?) {
      e ?: return
      val child = recyclerView?.findChildViewUnder(e.x, e.y)
      child ?: return
      val position = recyclerView?.getChildAdapterPosition(child)
      position ?: return
      child.onTouchEvent(getTransformedMotionEvent(e,child))
    }

  }


}