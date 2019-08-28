package yuluyao.frog.click

import android.view.GestureDetector
import android.view.MotionEvent

abstract class FrogClickListener : BaseTouchListener() {

  abstract fun onItemClicked(position: Int)

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = SingleTapUpListener()

  inner class SingleTapUpListener : GestureDetector.SimpleOnGestureListener() {

    override fun onDown(e: MotionEvent?): Boolean {
      e ?: return false
      val itemView = recyclerView?.findChildViewUnder(e.x, e.y)
      itemView ?: return false
      itemView.isClickable = true
      return super.onDown(e)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      val itemView = recyclerView?.findChildViewUnder(e.x, e.y)
      itemView ?: return false
      val position = recyclerView?.getChildAdapterPosition(itemView)
      position ?: return false

      itemView.dispatchTouchEvent(getTransformedMotionEvent(e, itemView))
      onItemClicked(position)
      return true
    }

//    override fun onShowPress(e: MotionEvent?) {
//      e ?: return
//      val itemView = recyclerView?.findChildViewUnder(e.x, e.y)
//      itemView ?: return
//      val position = recyclerView?.getChildAdapterPosition(itemView)
//      position ?: return
//      itemView.dispatchTouchEvent(getTransformedMotionEvent(e,itemView))
//    }

  }


}