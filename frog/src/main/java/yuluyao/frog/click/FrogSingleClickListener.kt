package yuluyao.frog.click

import android.view.GestureDetector
import android.view.MotionEvent

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

    override fun onDown(e: MotionEvent?): Boolean {
      e ?: return false
      val child = recyclerView?.findChildViewUnder(e.x, e.y)
      child ?: return false
      child.isClickable = true
      return super.onDown(e)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      val child = recyclerView?.findChildViewUnder(e.x, e.y)
      child ?: return false
      val position = recyclerView?.getChildAdapterPosition(child)
      position ?: return false

      val clickTimeMills = System.currentTimeMillis()
      if (clickTimeMills - lastClickTimeMills < timeGap) {
        lastClickTimeMills = clickTimeMills
        return false
      }

      lastClickTimeMills = clickTimeMills
      child.dispatchTouchEvent(getTransformedMotionEvent(e, child))
      onItemClicked(position)
      return true
    }


  }


}