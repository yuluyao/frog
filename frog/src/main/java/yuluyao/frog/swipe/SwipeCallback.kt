package yuluyao.frog.swipe

import android.graphics.Canvas

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 18:46
 */
class SwipeCallback : ItemTouchHelper.Callback() {

  override fun getMovementFlags(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder): Int {
    val dragFlags: Int
    val swipeFlags: Int
    if (isLinearVerticalLayout(recyclerView)) {
      dragFlags = 0
      swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    } else {
      throw RuntimeException(
        "Swipe gesture must be act on linear vertical RecyclerView!Set LinearLayoutManager to your RecyclerView!")
    }
    return makeMovementFlags(dragFlags, swipeFlags)
  }

  override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                      target: RecyclerView.ViewHolder): Boolean {
    return false
  }

  override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    //List list = ((ChickAdapter) recyclerView.getAdapter()).getData();

  }

  override fun onChildDraw(c: Canvas, recyclerView: RecyclerView,
                           viewHolder: RecyclerView.ViewHolder,
                           dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
    //if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
    //  if (Math.abs(dX) <= recyclerView.getWidth()) {
    //    viewHolder.itemView.scrollTo(-(int) dX, 0);
    //  }
    //}
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)

  }

  private fun isLinearVerticalLayout(recyclerView: RecyclerView): Boolean {
    //init LayoutManager type
    val layoutManager = recyclerView.layoutManager
    return if (layoutManager is LinearLayoutManager) {
      if (layoutManager is GridLayoutManager) {
        //GridLayoutManager
        //layout_type = LAYOUT_GRID;
        false
      } else {
        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
          //LinearLayoutManager -- vertical
          //layout_type = LAYOUT_VERTICAL;
          true
        } else {
          //LinearLayoutManager -- horizontal
          //layout_type = LAYOUT_HORIZONTAL;
          false
        }
      }
    } else {
      if ((layoutManager as StaggeredGridLayoutManager).orientation == StaggeredGridLayoutManager.VERTICAL) {
        //StaggeredGridLayoutManager -- vertical
        //layout_type = LAYOUT_STAGGERED_GRID_VERTICAL;
        false
      } else {
        //StaggeredGridLayoutManager -- horizontal
        //layout_type = LAYOUT_STAGGERED_GRID_HORIZONTAL;
        false
      }
    }
  }
}
