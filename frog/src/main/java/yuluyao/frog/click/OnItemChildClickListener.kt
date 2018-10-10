package yuluyao.frog.click

import android.graphics.Rect
import android.graphics.Region
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

abstract class OnItemChildClickListener : BaseTouchListener() {

  abstract fun onChildClicked(position: Int, viewId: Int)
  abstract val listenedChildrenIds: IntArray

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = Listener()

  inner class Listener : GestureDetector.SimpleOnGestureListener() {
    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      val child = recyclerView?.findChildViewUnder(e.x, e.y) as ViewGroup?
      child ?: return false

      val clickView = findTarget(child, e.rawX.toInt(), e.rawY.toInt())
      clickView ?: return false

      val position = recyclerView?.getChildAdapterPosition(child)
      position ?: return false
      onChildClicked(position, clickView.id)
      return true
    }

    private fun findTarget(parent: ViewGroup, rawX: Int, rawY: Int): View? {
      var target: View? = if (listenedChildrenIds.contains(parent.id)) parent else null
      for (i in 0 until parent.childCount) {
        val childAti = parent.getChildAt(i)
        if (childAti is ViewGroup) {
          target = findTarget(childAti, rawX, rawY)
        } else {
          if (!listenedChildrenIds.contains(childAti.id)) continue
          val r = Rect()
          childAti.getGlobalVisibleRect(r)
          val region = Region(r)
          if (region.contains(rawX, rawY)) {
            target = childAti
          }
        }
      }
      return target
    }
  }
}