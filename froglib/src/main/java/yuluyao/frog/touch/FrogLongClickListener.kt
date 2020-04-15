package yuluyao.frog.touch

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

abstract class FrogLongClickListener : BaseTouchListener() {
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

    override fun onLongPress(e: MotionEvent?) {
      super.onLongPress(e)
      e ?: return
      itemView ?: return
      recyclerView ?: return

      val position = recyclerView!!.getChildAdapterPosition(itemView!!)
      if (position == -1) {
        return
      }

      onItemClicked(position)
      return
    }


  }


}