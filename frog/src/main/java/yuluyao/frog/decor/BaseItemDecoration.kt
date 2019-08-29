package yuluyao.frog.decor

import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View


/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 17:39
 */
open class BaseItemDecoration : RecyclerView.ItemDecoration() {

  protected var layout_type = LAYOUT_UNSPECIFIED
  protected var mRecyclerView: RecyclerView? = null

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                              state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    if (layout_type == LAYOUT_UNSPECIFIED) {
      getLayoutType(parent)
    }
  }

  private fun getLayoutType(recyclerView: RecyclerView) {
    this.mRecyclerView = recyclerView
    //init LayoutManager type
    val layoutManager = recyclerView.layoutManager
    if (layoutManager is LinearLayoutManager) {
      if (layoutManager is GridLayoutManager) {
        //GridLayoutManager
        layout_type = LAYOUT_GRID
      } else {
        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
          //LinearLayoutManager -- vertical
          layout_type = LAYOUT_VERTICAL
        } else {
          //LinearLayoutManager -- horizontal
          layout_type = LAYOUT_HORIZONTAL
        }
      }
    } else {
      if ((layoutManager as StaggeredGridLayoutManager).orientation == StaggeredGridLayoutManager.VERTICAL) {
        //StaggeredGridLayoutManager -- vertical
        layout_type = LAYOUT_STAGGERED_GRID_VERTICAL
      } else {
        //StaggeredGridLayoutManager -- horizontal
        layout_type = LAYOUT_STAGGERED_GRID_HORIZONTAL
      }
    }


  }

  companion object {

    const val LAYOUT_UNSPECIFIED = -1//未初始化
    const val LAYOUT_VERTICAL = 0//竖向
    const val LAYOUT_HORIZONTAL = 1//横向
    const val LAYOUT_GRID = 2//表格
    const val LAYOUT_STAGGERED_GRID_VERTICAL = 3//瀑布--vertical
    const val LAYOUT_STAGGERED_GRID_HORIZONTAL = 4//瀑布--horizontal
  }
}
