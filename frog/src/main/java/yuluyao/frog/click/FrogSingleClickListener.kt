package yuluyao.frog.click

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

/**
 * 单击监听，在连续点击中，只有第一次生效,
 * timeGap: 点击最小间隔，小于此间隔的点击不被响应
 */
abstract class FrogSingleClickListener(val timeGap: Long = 500L) : BaseTouchListener() {

  abstract fun onItemClicked(position: Int)

  private var lastClickTimeMills = 0L

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = SingleTapUpListener()

  inner class SingleTapUpListener : GestureDetector.SimpleOnGestureListener() {
    private var itemView: View? = null

    override fun onDown(e: MotionEvent?): Boolean {
      e ?: return false
      itemView = recyclerView?.findChildViewUnder(e.x, e.y)
//      itemView ?: return false
      itemView?.isClickable = true
      return super.onDown(e)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      itemView ?: return false

      val position = recyclerView?.getChildAdapterPosition(itemView!!)
      position ?: return false

      // 丢弃多余的点击
      val clickTimeMills = System.currentTimeMillis()
      if (clickTimeMills - lastClickTimeMills < timeGap) {
        lastClickTimeMills = clickTimeMills
        return false
      }
      lastClickTimeMills = clickTimeMills

      itemView!!.dispatchTouchEvent(getTransformedMotionEvent(e, itemView!!))
      onItemClicked(position)
      return true
    }


  }


}