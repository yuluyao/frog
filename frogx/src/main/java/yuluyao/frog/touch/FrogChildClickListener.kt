package yuluyao.frog.touch

import android.graphics.Rect
import android.graphics.Region
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

abstract class FrogChildClickListener : BaseTouchListener() {

  abstract fun onChildClicked(position: Int, viewId: Int)
  abstract val listenedChildrenIds: IntArray

  override val gestureListener: GestureDetector.SimpleOnGestureListener
    get() = Listener()

  inner class Listener : GestureDetector.SimpleOnGestureListener() {
    private var itemView: ViewGroup? = null

    override fun onDown(e: MotionEvent?): Boolean {
      e ?: return false
      try {
        itemView = recyclerView?.findChildViewUnder(e.x, e.y) as ViewGroup?
      } catch (e: Exception) {
        throw Exception("item view is not a ViewGroup!")
      }
      itemView ?: return false

      // 监听的 View 设为 可点击
      if (listenedChildrenIds.contains(itemView!!.id)) {
        itemView!!.isClickable = true
      }
      for (id in listenedChildrenIds.indices) {
        val v = itemView!!.findViewById<View>(id)
        v?.isClickable = true
      }

      return super.onDown(e)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
      e ?: return false
      itemView ?: return false

      // 查找被点击的 View
      target = null
      val clickView = findTarget(itemView!!, e.rawX.toInt(), e.rawY.toInt())
      clickView ?: return false

      val position = recyclerView?.getChildAdapterPosition(itemView!!)
      position ?: return false

      itemView!!.dispatchTouchEvent(getTransformedMotionEvent(e, itemView!!))
      onChildClicked(position, clickView.id)
      return true
    }

    var target: View? = null
    private fun findTarget(parent: ViewGroup, rawX: Int, rawY: Int): View? {
      if (listenedChildrenIds.contains(parent.id)) {
        if (isClickInside(parent, rawX, rawY)) {
          target = parent
        }
      }
      for (i in 0 until parent.childCount) {
        val childAti = parent.getChildAt(i)
        if (childAti is ViewGroup) {
          target = findTarget(childAti, rawX, rawY)
        } else {
          if (!listenedChildrenIds.contains(childAti.id)) continue
          if (isClickInside(childAti, rawX, rawY)) {
            target = childAti
          }
        }
      }
      return target
    }

    private fun isClickInside(view: View, rawX: Int, rawY: Int): Boolean {
      val r = Rect()
      view.getGlobalVisibleRect(r)
      val region = Region(r)
      return region.contains(rawX, rawY)
    }
  }


}