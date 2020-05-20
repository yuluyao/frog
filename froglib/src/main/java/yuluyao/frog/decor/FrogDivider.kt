package yuluyao.frog.decor

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:10
 */
class FrogDivider(private val width: Float = 2F,
                  private val colorRes: Int = android.R.color.transparent,
                  private val includePadding: Boolean = false,
                  private val includeLastItem: Boolean = true,
                  private val includeFirstItem: Boolean = false) : BaseItemDecoration() {
  private var widthPixels: Float = 0f
  private var paint: Paint = Paint()

  // 使用全局变量，避免在onDraw()方法中频繁创建对象
  private var bounds = Rect()

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    if (mLayoutType != UNSPECIFIED) {
      //init divider width and color
      widthPixels = parent.context.resources.displayMetrics.density * width
      paint.color = parent.context.resources.getColor(colorRes)
    }

    var spanCount = 0
    when (mLayoutType) {
      LINEAR_VERTICAL -> {
        outRect.set(0, 0, 0, widthPixels.toInt())
        return // 线性 manager 简单处理
      }
      LINEAR_HORIZONTAL -> {
        outRect.set(0, 0, widthPixels.toInt(), 0)
        return // 线性 manager 简单处理
      }
      /* ------------------ */
      GRID -> spanCount = (parent.layoutManager as GridLayoutManager).spanCount
      STAGGERED_GRID_VERTICAL, STAGGERED_GRID_HORIZONTAL -> spanCount = (parent.layoutManager as StaggeredGridLayoutManager).spanCount
    }

    var l = widthPixels / 2
    var t = widthPixels / 2
    var r = widthPixels / 2
    var b = widthPixels / 2
    val childCount = parent.adapter!!.itemCount
    val itemPosition = parent.getChildAdapterPosition(view)
    if (isTop(itemPosition, spanCount, childCount, view)) {
      // 如果是第一行，则不需要绘制上边
      t = 0f
    }
    if (isLeft(itemPosition, spanCount, childCount, view)) {
      // 如果是第一列，则不需要绘制左边
      l = 0f
    }
    if (isRight(itemPosition, spanCount, childCount, view)) {
      // 如果是最后一列，则不需要绘制右边
      r = 0f
    }
    if (isBottom(itemPosition, spanCount, childCount, view)) {
      // 如果是最后一行，则不需要绘制底部
      b = 0f;
    }
    outRect.set(l.toInt(), t.toInt(), r.toInt(), b.toInt())
  }


  private fun isTop(pos: Int, spanCount: Int, childCount: Int, itemView: View): Boolean {
    when (mLayoutType) {
      GRID -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)
        return spanGroupIndex == 0
      }
      STAGGERED_GRID_VERTICAL -> return pos < spanCount
      STAGGERED_GRID_HORIZONTAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return spanIndex == 0
      }
    }
    return false
  }


  private fun isLeft(pos: Int, spanCount: Int, childCount: Int, itemView: View): Boolean {
    when (mLayoutType) {
      GRID -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)
        return spanIndex == 0
      }
      STAGGERED_GRID_VERTICAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return spanIndex == 0
      }
      STAGGERED_GRID_HORIZONTAL -> return pos < spanCount
    }
    return false
  }

  private fun isRight(pos: Int, spanCount: Int, childCount: Int, itemView: View): Boolean {
    when (mLayoutType) {
      // 如果是最后一列，则不需要绘制右边
      GRID -> {
        val sizeLookup = (mRecyclerView.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)
        return spanIndex + spanSize - 1 == spanCount - 1
      }
      // 如果是最后一列，则不需要绘制右边
      STAGGERED_GRID_VERTICAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return spanIndex == spanCount - 1
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
      // 如果是最后一行，则不需要绘制底部
      GRID -> {
        // todo 暂未实现
      }
      // 如果是最后一行，则不需要绘制底部
      STAGGERED_GRID_VERTICAL -> {
        // todo 暂未实现
      }
      // 如果是最后一行，则不需要绘制底部
      STAGGERED_GRID_HORIZONTAL -> {
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        return spanIndex == spanCount - 1
      }
    }
    return false
  }


  override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    super.onDraw(c, parent, state)
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
    } /*else {
      top = 0
      bottom = parent.height
      left = 0
      right = parent.width
    }*/
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
        LINEAR_VERTICAL -> {
          // draw bottom
          canvas.drawRect(left_f, top_inner, right_f, bottom_f, paint)
        }
        LINEAR_HORIZONTAL -> {
          // draw right
          canvas.drawRect(left_inner, top_f, right_f, bottom_f, paint)
        }
        GRID, STAGGERED_GRID_VERTICAL, STAGGERED_GRID_HORIZONTAL -> {
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
