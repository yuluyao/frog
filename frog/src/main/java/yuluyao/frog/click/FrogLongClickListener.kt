package yuluyao.frog.click

import android.view.GestureDetector
import android.view.MotionEvent

abstract class FrogLongClickListener : BaseTouchListener() {

  abstract fun onItemClicked(position: Int)

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = SingleTapUpListener()

  inner class SingleTapUpListener : GestureDetector.SimpleOnGestureListener() {

    override fun onLongPress(e: MotionEvent?) {
      super.onLongPress(e)
      e ?: return
      val child = recyclerView?.findChildViewUnder(e.x, e.y)
      child ?: return
      val position = recyclerView?.getChildAdapterPosition(child)
      position ?: return
      child.onTouchEvent(getTransformedMotionEvent(e,child))
      onItemClicked(position)
      return
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