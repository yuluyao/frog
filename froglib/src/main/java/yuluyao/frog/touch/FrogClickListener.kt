package yuluyao.frog.touch

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

abstract class FrogClickListener : BaseTouchListener() {
  override val listenedChildrenIds: IntArray = intArrayOf() // 这里不用处理子View手势
  abstract fun onItemClicked(position: Int)

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = SingleTapUpListener()

  inner class SingleTapUpListener : GestureDetector.SimpleOnGestureListener() {
    private var itemView: View? = null

    override fun onDown(e: MotionEvent?): Boolean {
      e ?: return false
      itemView = recyclerView?.findChildViewUnder(e.x, e.y)
      // 设置 item view 为可点击
      itemView?.isClickable = true
      return super.onDown(e)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      itemView ?: return false
      recyclerView ?: return false

      val position = recyclerView!!.getChildAdapterPosition(itemView!!)
      if (position == -1) {
        return false
      }

      itemView!!.dispatchTouchEvent(getTransformedMotionEvent(e, itemView!!))
      onItemClicked(position)
      return true
    }

  }


}