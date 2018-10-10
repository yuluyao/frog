package yuluyao.frog.click

import android.view.GestureDetector
import android.view.MotionEvent

abstract class OnItemClickListener : BaseTouchListener() {

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
      onItemClicked(position)
      return true
    }

  }


}