package yuluyao.frog.click

import android.view.GestureDetector
import android.view.MotionEvent

abstract class FrogLongClickListener : BaseTouchListener() {

  abstract fun onItemClicked(position: Int)

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = SingleTapUpListener()

  inner class SingleTapUpListener : GestureDetector.SimpleOnGestureListener() {

    override fun onDown(e: MotionEvent?): Boolean {
      e ?: return false
      val child = recyclerView?.findChildViewUnder(e.x, e.y)
      child ?: return false
      child.isClickable = true
      return super.onDown(e)
    }

    override fun onLongPress(e: MotionEvent?) {
      super.onLongPress(e)
      e ?: return
      val child = recyclerView?.findChildViewUnder(e.x, e.y)
      child ?: return
      val position = recyclerView?.getChildAdapterPosition(child)
      position ?: return

      onItemClicked(position)
      return
    }



  }


}