package yuluyao.frog.decor

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import java.lang.RuntimeException


/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 17:39
 */
open class BaseItemDecoration : RecyclerView.ItemDecoration() {
  companion object {
    const val UNSPECIFIED = 0
    const val LINEAR_VERTICAL = 1
    const val LINEAR_HORIZONTAL = 2
    const val GRID_VERTICAL = 3
    const val GRID_HORIZONTAL = 4
    const val STAGGERED_GRID_VERTICAL = 5
    const val STAGGERED_GRID_HORIZONTAL = 6
  }

  protected var mLayoutType = UNSPECIFIED
  protected lateinit var mRecyclerView: RecyclerView

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    if (mLayoutType == UNSPECIFIED) {
      mRecyclerView = parent
      initLayoutManagerType(parent)
    }
  }

  private fun initLayoutManagerType(recyclerView: RecyclerView) {
    mLayoutType = when (val layoutManager = recyclerView.layoutManager) {
      is GridLayoutManager -> if (layoutManager.orientation == LinearLayoutManager.VERTICAL) GRID_VERTICAL else GRID_HORIZONTAL
      is LinearLayoutManager -> if (layoutManager.orientation == LinearLayoutManager.VERTICAL) LINEAR_VERTICAL else LINEAR_HORIZONTAL
      is StaggeredGridLayoutManager -> if (layoutManager.orientation == StaggeredGridLayoutManager.VERTICAL) STAGGERED_GRID_VERTICAL else STAGGERED_GRID_HORIZONTAL
      else -> throw RuntimeException("not supported LayoutManager type!")
    }
  }

}
