package yuluyao.frog

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:10
 */
class Divider(private val width: Float = 2F) : RecyclerView.ItemDecoration() {

  companion object {
    const val UNSPECIFIED = 0
    const val LINEAR_VERTICAL = 1
    const val LINEAR_HORIZONTAL = 2
    const val GRID_VERTICAL = 3
    const val GRID_HORIZONTAL = 4
    const val STAGGERED_GRID_VERTICAL = 5
    const val STAGGERED_GRID_HORIZONTAL = 6
  }

  private var colorInt: Int = 0x00000000
  private var includeEdge: Boolean = false

  constructor(width: Float = 2F, colorInt: Int, includeEdge: Boolean) : this(width) {
    this.colorInt = colorInt
    this.includeEdge = includeEdge
  }

  private var mLayoutType = UNSPECIFIED
  private lateinit var mRecyclerView: RecyclerView

  private var widthPixels: Float = 0f
  private var paint: Paint = Paint()

  // 使用全局变量，避免在onDraw()方法中频繁创建对象
  private var bounds = Rect()

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    if (mLayoutType == UNSPECIFIED) {
      mRecyclerView = parent
      widthPixels = parent.context.resources.displayMetrics.density * width
      paint.color = colorInt
      initLayoutManagerType(parent)
    }

    val childCount = parent.adapter!!.itemCount
    val itemPosition = parent.getChildAdapterPosition(view)

    var out_left = widthPixels / 2
    var out_top = widthPixels / 2
    var out_right = widthPixels / 2
    var out_bottom = widthPixels / 2

    when (mLayoutType) {
      LINEAR_VERTICAL -> {
        if (isTop(itemPosition, 1, childCount, view)) {
          out_top = if (includeEdge) widthPixels else 0f
        }
        if (isLeft(itemPosition, 1, childCount, view)) {
          out_left = if (includeEdge) widthPixels else 0f
        }
        if (isRight(itemPosition, 1, childCount, view)) {
          out_right = if (includeEdge) widthPixels else 0f
        }
        if (isBottom(itemPosition, 1, childCount, view)) {
          out_bottom = if (includeEdge) widthPixels else 0f
        }
      }
      LINEAR_HORIZONTAL -> {
        if (isTop(itemPosition, 1, childCount, view)) {
          out_top = if (includeEdge) widthPixels else 0f
        }
        if (isLeft(itemPosition, 1, childCount, view)) {
          out_left = if (includeEdge) widthPixels else 0f
        }
        if (isRight(itemPosition, 1, childCount, view)) {
          out_right = if (includeEdge) widthPixels else 0f
        }
        if (isBottom(itemPosition, 1, childCount, view)) {
          out_bottom = if (includeEdge) widthPixels else 0f
        }
      }
    }

    when (mLayoutType) {
      GRID_VERTICAL, GRID_HORIZONTAL, STAGGERED_GRID_VERTICAL, STAGGERED_GRID_HORIZONTAL -> {
        val spanCount = when (mLayoutType) {
          GRID_VERTICAL, GRID_HORIZONTAL -> (parent.layoutManager as GridLayoutManager).spanCount
          STAGGERED_GRID_VERTICAL, STAGGERED_GRID_HORIZONTAL -> (parent.layoutManager as StaggeredGridLayoutManager).spanCount
          else -> 0
        }
        if (isTop(itemPosition, spanCount, childCount, view)) {
          // 如果是第一行，则不需要绘制上边
          out_top = if (includeEdge) widthPixels else 0f
        }
        if (isLeft(itemPosition, spanCount, childCount, view)) {
          // 如果是第一列，则不需要绘制左边
          out_left = if (includeEdge) widthPixels else 0f
        }
        if (isRight(itemPosition, spanCount, childCount, view)) {
          // 如果是最后一列，则不需要绘制右边
          out_right = if (includeEdge) widthPixels else 0f
        }
        if (isBottom(itemPosition, spanCount, childCount, view)) {
          // 如果是最后一行，则不需要绘制底部
          out_bottom = if (includeEdge) widthPixels else 0f
        }
      }
    }


    outRect.set(out_left.toInt(), out_top.toInt(), out_right.toInt(), out_bottom.toInt())
  }

  private fun initLayoutManagerType(recyclerView: RecyclerView) {
    mLayoutType = when (val layoutManager = recyclerView.layoutManager) {
      is GridLayoutManager -> if (layoutManager.orientation == LinearLayoutManager.VERTICAL) GRID_VERTICAL else GRID_HORIZONTAL
      is LinearLayoutManager -> if (layoutManager.orientation == LinearLayoutManager.VERTICAL) LINEAR_VERTICAL else LINEAR_HORIZONTAL
      is StaggeredGridLayoutManager -> if (layoutManager.orientation == StaggeredGridLayoutManager.VERTICAL) STAGGERED_GRID_VERTICAL else STAGGERED_GRID_HORIZONTAL
      else -> throw RuntimeException("not supported LayoutManager type!")
    }
  }


  private fun isTop(pos: Int, spanCount: Int, childCount: Int, itemView: View): Boolean {
    when (mLayoutType) {
      LINEAR_VERTICAL -> return pos == 0
      LINEAR_HORIZONTAL -> return true
      GRID_VERTICAL -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
//        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
//        val spanSize = sizeLookup.getSpanSize(pos)
        return spanGroupIndex == 0
      }
      GRID_HORIZONTAL -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
//        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
//        val spanSize = sizeLookup.getSpanSize(pos)
        return spanIndex == 0
      }
      STAGGERED_GRID_VERTICAL -> return pos < spanCount
      STAGGERED_GRID_HORIZONTAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return if (layoutParams.isFullSpan) true else spanIndex == 0
//        return spanIndex == 0
      }
    }
    return false
  }


  private fun isLeft(pos: Int, spanCount: Int, childCount: Int, itemView: View): Boolean {
    when (mLayoutType) {
      LINEAR_VERTICAL -> return true
      LINEAR_HORIZONTAL -> return pos == 0
      GRID_VERTICAL -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
//        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
//        val spanSize = sizeLookup.getSpanSize(pos)
        return spanIndex == 0
      }
      GRID_HORIZONTAL -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
//        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
//        val spanSize = sizeLookup.getSpanSize(pos)
        return spanGroupIndex == 0
      }
      STAGGERED_GRID_VERTICAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return if (layoutParams.isFullSpan) true else spanIndex == 0
//        return spanIndex == 0
      }
      STAGGERED_GRID_HORIZONTAL -> return pos < spanCount
    }
    return false
  }

  private fun isRight(pos: Int, spanCount: Int, childCount: Int, itemView: View): Boolean {
    when (mLayoutType) {
      LINEAR_VERTICAL -> return true
      LINEAR_HORIZONTAL -> return pos == childCount - 1
      // 如果是最后一列，则不需要绘制右边
      GRID_VERTICAL -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
//        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)
        return spanIndex + spanSize - 1 == spanCount - 1
      }
      GRID_HORIZONTAL -> {
        // todo 暂未实现
      }
      // 如果是最后一列，则不需要绘制右边
      STAGGERED_GRID_VERTICAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return if (layoutParams.isFullSpan) true else spanIndex == spanCount - 1
//        return spanIndex == spanCount - 1
      }
      // 如果是最后一列，则不需要绘制右边
      STAGGERED_GRID_HORIZONTAL -> {
        // todo 暂未实现
      }
    }
    return false
  }

  private fun isBottom(pos: Int, spanCount: Int, childCount: Int, itemView: View): Boolean {
    when (mLayoutType) {
      LINEAR_VERTICAL -> return pos == childCount - 1
      LINEAR_HORIZONTAL -> return true
      // 如果是最后一行，则不需要绘制底部
      GRID_VERTICAL -> {
        // todo 暂未实现
//        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
//        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
//        val maxSpanGroupIndex = sizeLookup.getSpanGroupIndex(childCount-1, spanCount)
//        return spanGroupIndex == maxSpanGroupIndex
      }
      GRID_HORIZONTAL -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
//        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)
        return spanIndex + spanSize - 1 == spanCount - 1
      }
      // 如果是最后一行，则不需要绘制底部
      STAGGERED_GRID_VERTICAL -> {
        // todo 暂未实现
      }
      // 如果是最后一行，则不需要绘制底部
      STAGGERED_GRID_HORIZONTAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return if (layoutParams.isFullSpan) true else spanIndex == spanCount - 1
//        return spanIndex == spanCount - 1
      }
    }
    return false
  }


  override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    super.onDraw(c, parent, state)
    if (colorInt == 0) {
      // if transparent, need not to draw
      return
    }
    drawDecoration(c, parent)
  }


  private fun drawDecoration(canvas: Canvas, parent: RecyclerView) {
    canvas.save()

    var top: Int = parent.top
    var bottom: Int = parent.bottom
    var left: Int = parent.left
    var right: Int = parent.right

    if (parent.clipToPadding) {
      top += parent.paddingTop
      bottom -= parent.paddingBottom
      left += parent.paddingLeft
      right -= parent.paddingRight
    }
    canvas.clipRect(left, top, right, bottom)

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val itemView = parent.getChildAt(i)
      parent.getDecoratedBoundsWithMargins(itemView, bounds)

      val left_f = bounds.left.toFloat()
      val top_f = bounds.top.toFloat()
      val right_f = bounds.right.toFloat()
      val bottom_f = bounds.bottom.toFloat()

      val right_inner = bounds.left + widthPixels
      val bottom_inner = bounds.top + widthPixels
      val left_inner = bounds.right - widthPixels
      val top_inner = bounds.bottom - widthPixels
      when (mLayoutType) {
        LINEAR_VERTICAL, LINEAR_HORIZONTAL, GRID_VERTICAL, STAGGERED_GRID_VERTICAL, STAGGERED_GRID_HORIZONTAL -> {
          // draw left
          canvas.drawRect(left_f, top_f, right_inner, bottom_f, paint)
          // draw top
          canvas.drawRect(left_f, top_f, right_f, bottom_inner, paint)
          // draw right
          canvas.drawRect(left_inner, top_f, right_f, bottom_f, paint)
          // draw bottom
          canvas.drawRect(left_f, top_inner, right_f, bottom_f, paint)
        }
      }
    }

    canvas.restore()
  }


}
