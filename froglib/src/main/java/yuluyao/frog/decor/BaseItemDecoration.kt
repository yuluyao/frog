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
    const val UNSPECIFIED = -1//未初始化
    const val LINEAR_VERTICAL = 0//竖向
    const val LINEAR_HORIZONTAL = 1//横向
    const val GRID = 2//表格
    const val STAGGERED_GRID_VERTICAL = 3//瀑布--vertical
    const val STAGGERED_GRID_HORIZONTAL = 4//瀑布--horizontal
  }

  protected var mLayoutType = UNSPECIFIED
  protected lateinit var mRecyclerView: RecyclerView

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    if (mLayoutType == UNSPECIFIED) {
      getLayoutType(parent)
    }
  }

  private fun getLayoutType(recyclerView: RecyclerView) {
    this.mRecyclerView = recyclerView
    mLayoutType = when (val layoutManager = recyclerView.layoutManager) {
      is GridLayoutManager -> GRID
      is LinearLayoutManager -> if (layoutManager.orientation == LinearLayoutManager.VERTICAL) LINEAR_VERTICAL else LINEAR_HORIZONTAL
      is StaggeredGridLayoutManager -> if (layoutManager.orientation == StaggeredGridLayoutManager.VERTICAL) STAGGERED_GRID_VERTICAL else STAGGERED_GRID_HORIZONTAL
      else -> {
        throw RuntimeException("not supported LayoutManager type!")
      }
    }
//    if (layoutManager is LinearLayoutManager) {
//      if (layoutManager is GridLayoutManager) {
//        //GridLayoutManager
//        layout_type = GRID
//      } else {
//        if (layoutManager.orientation == LinearLayoutManager.VERTICAL) {
//          //LinearLayoutManager -- vertical
//          layout_type = LINEAR_VERTICAL
//        } else {
//          //LinearLayoutManager -- horizontal
//          layout_type = LINEAR_HORIZONTAL
//        }
//      }
//    } else {
//      if ((layoutManager as StaggeredGridLayoutManager).orientation == StaggeredGridLayoutManager.VERTICAL) {
//        //StaggeredGridLayoutManager -- vertical
//        layout_type = STAGGERED_GRID_VERTICAL
//      } else {
//        //StaggeredGridLayoutManager -- horizontal
//        layout_type = STAGGERED_GRID_HORIZONTAL
//      }
//    }


  }


}
