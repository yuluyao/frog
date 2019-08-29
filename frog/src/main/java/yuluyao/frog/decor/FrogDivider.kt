package yuluyao.frog.decor

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.View

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlin.math.roundToInt

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:10
 */
class FrogDivider(private val width: Float = 2F,
                  private val colorRes: Int = android.R.color.transparent) : BaseItemDecoration() {
  private var widthPixels: Float = 0.toFloat()
  private var paint: Paint? = null

  init {
    paint = Paint()
  }

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                              state: RecyclerView.State) {
    super.getItemOffsets(outRect, view, parent, state)
    if (layout_type != LAYOUT_UNSPECIFIED) {
      //init divider width and color
      widthPixels = parent.context.resources.displayMetrics.density * width
      paint!!.color = parent.context.resources.getColor(colorRes)
    }

    var spanCount = 0
    when (layout_type) {
      LAYOUT_VERTICAL -> {
        outRect.set(0, 0, 0, widthPixels.toInt())
        return // 线性 manager 简单处理
      }
      LAYOUT_HORIZONTAL -> {
        outRect.set(0, 0, widthPixels.toInt(), 0)
        return // 线性 manager 简单处理
      }
      /* ------------------ */
      LAYOUT_GRID -> spanCount = (parent.layoutManager as GridLayoutManager).spanCount
      LAYOUT_STAGGERED_GRID_VERTICAL, LAYOUT_STAGGERED_GRID_HORIZONTAL -> spanCount = (parent.layoutManager as StaggeredGridLayoutManager).spanCount
    }

    var l = widthPixels / 2
    var t = widthPixels / 2
    var r = widthPixels / 2
    var b = widthPixels / 2
    val childCount = parent.adapter!!.itemCount
    val itemPosition = parent.getChildAdapterPosition(view)
    if (isTop(itemPosition, spanCount, childCount)) {
      // 如果是第一行，则不需要绘制上边
      t = 0f
    }
    if (isLeft(itemPosition, spanCount, childCount)) {
      // 如果是第一列，则不需要绘制左边
      l = 0f
    }
    if (isRight(itemPosition, spanCount, childCount)) {
      // 如果是最后一列，则不需要绘制右边
      r = 0f
    }
    /*if (isBottom(itemPosition, spanCount, childCount)) {
      // 如果是最后一行，则不需要绘制底部
      b = 0f;
    }*/
    outRect.set(l.toInt(), t.toInt(), r.toInt(), b.toInt())
  }


  private fun isTop(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID -> {
        val sizeLookup = (mRecyclerView!!.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)

        if (spanGroupIndex == 0 && spanIndex + spanSize <= spanCount) {
          return true
        }
      }
      LAYOUT_STAGGERED_GRID_VERTICAL -> {
//        val result2 = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
//        if (pos >= result2) {
//          return true
//        }

        return pos < spanCount
      }
      LAYOUT_STAGGERED_GRID_HORIZONTAL -> if ((pos + 1) % spanCount == 0) {
        return true
      }
    }
    return false
  }


  private fun isLeft(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID -> {
        val sizeLookup = (mRecyclerView!!.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)

        if (spanIndex == 0) {
          return true
        }
      }
      LAYOUT_STAGGERED_GRID_VERTICAL -> {
        val holder = mRecyclerView!!.findViewHolderForAdapterPosition(pos)
        val itemView = holder?.itemView ?: return false
        val layoutParams = itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex
        Log.i("vegeta", "spanIndex = " + spanIndex)
        return spanIndex == 0
      }
      LAYOUT_STAGGERED_GRID_HORIZONTAL -> {
        val result = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result) {
          return true
        }
      }
    }

    return false
  }

  private fun isRight(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      // 如果是最后一列，则不需要绘制右边
      LAYOUT_GRID -> {
        val sizeLookup = (mRecyclerView!!.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)

        if (spanIndex + spanSize - 1 == spanCount - 1) {
          return true
        }
      }
      // 如果是最后一列，则不需要绘制右边
      LAYOUT_STAGGERED_GRID_VERTICAL -> if ((pos + 1) % spanCount == 0) {
        return true
      }
      // 如果是最后一列，则不需要绘制右边
      LAYOUT_STAGGERED_GRID_HORIZONTAL -> {
        val result = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result) {
          return true
        }
      }
    }//        if ((pos + 1) % spanCount == 0) {
    //          return true;
    //        }

    return false
  }

  private fun isBottom(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID // 如果是最后一行，则不需要绘制底部
      -> {
        val sizeLookup = (mRecyclerView!!.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)

        // 暂未实现判断方法
        return false
      }
      LAYOUT_STAGGERED_GRID_VERTICAL // 如果是最后一行，则不需要绘制底部
      -> {
        val result2 = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result2) {
          return true
        }
      }
      LAYOUT_STAGGERED_GRID_HORIZONTAL// 如果是最后一行，则不需要绘制底部
      -> if ((pos + 1) % spanCount == 0) {
        return true
      }
    }
    return false
  }


  /*
  private fun isFirstColumn(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID -> if (pos % spanCount == 0) {
        return true
      }
      LAYOUT_STAGGERED_GRID_VERTICAL -> if (pos % spanCount == 0) {
        return true
      }
      LAYOUT_STAGGERED_GRID_HORIZONTAL -> {
        val result = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result) {
          return true
        }
      }
    }

    return false
  }

  private fun isLastColumn(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID// 如果是最后一列，则不需要绘制右边
      -> if ((pos + 1) % spanCount == 0) {
        return true
      }
      LAYOUT_STAGGERED_GRID_VERTICAL// 如果是最后一列，则不需要绘制右边
      -> if ((pos + 1) % spanCount == 0) {
        return true
      }
      LAYOUT_STAGGERED_GRID_HORIZONTAL// 如果是最后一列，则不需要绘制右边
      -> {
        val result = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result) {
          return true
        }
      }
    }

    return false
  }


  private fun isFirstRaw(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID -> if (pos < spanCount) {
        return true
      }
      LAYOUT_STAGGERED_GRID_VERTICAL -> {
        val result2 = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result2) {
          return true
        }
      }
      LAYOUT_STAGGERED_GRID_HORIZONTAL -> if ((pos + 1) % spanCount == 0) {
        return true
      }
    }
    return false
  }


  private fun isLastRaw(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID // 如果是最后一行，则不需要绘制底部
      -> {
        val result = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result) {
          return true
        }
      }
      LAYOUT_STAGGERED_GRID_VERTICAL // 如果是最后一行，则不需要绘制底部
      -> {
        val result2 = childCount - if (childCount % spanCount == 0) spanCount else childCount % spanCount
        if (pos >= result2) {
          return true
        }
      }
      LAYOUT_STAGGERED_GRID_HORIZONTAL// 如果是最后一行，则不需要绘制底部
      -> if ((pos + 1) % spanCount == 0) {
        return true
      }
    }
    return false
  }
*/


  override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
    super.onDraw(c, parent, state)
    when (layout_type) {
      LAYOUT_VERTICAL -> drawVertical(c, parent)
      LAYOUT_HORIZONTAL -> drawHorizontal(c, parent)
      LAYOUT_GRID -> drawGrid(c, parent)
      LAYOUT_STAGGERED_GRID_VERTICAL -> drawGrid(c, parent)
      LAYOUT_STAGGERED_GRID_HORIZONTAL -> drawGrid(c, parent)
    }
  }

  private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
    canvas.save()
    val left = parent.paddingLeft
    val right = (parent.width - parent.paddingRight)
    val top = if (parent.clipToPadding) {
      parent.top + parent.paddingTop
    } else {
      parent.top
    }
    val bottom = if (parent.clipToPadding) {
      parent.bottom - parent.paddingBottom
    } else {
      parent.bottom
    }
    canvas.clipRect(left, top, right, bottom)

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val itemView = parent.getChildAt(i)
      val bounds = Rect()
      parent.getDecoratedBoundsWithMargins(itemView, bounds)
      canvas.drawRect(bounds, paint!!)

//      val itemBottom = (bounds.bottom + itemView.translationY.roundToInt()).toFloat()
//      val itemTop = itemBottom - widthPixels
//      canvas.drawRect(left.toFloat(), itemTop, right.toFloat(), itemBottom, paint!!)
    }
    canvas.restore()
  }

  private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
    canvas.save()
    val top = parent.paddingTop
    val bottom = (parent.height - parent.paddingBottom)
    val left = if (parent.clipToPadding) {
      parent.left + parent.paddingLeft
    } else {
      parent.left
    }
    val right = if (parent.clipToPadding) {
      parent.right - parent.paddingRight
    } else {
      parent.right
    }
    canvas.clipRect(left, top, right, bottom)

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val itemView = parent.getChildAt(i)
      val bounds = Rect()
      parent.getDecoratedBoundsWithMargins(itemView, bounds)
      canvas.drawRect(bounds, paint!!)

//      val itemRight = (bounds.right + itemView.translationY.roundToInt()).toFloat()
//      val itemLeft = itemRight - widthPixels
//      canvas.drawRect(itemLeft, top.toFloat(), itemRight, bottom.toFloat(), paint!!)
    }
    canvas.restore()
  }

  private fun drawGrid(canvas: Canvas, parent: RecyclerView) {
    val top: Int
    val bottom: Int
    val left: Int
    val right: Int
    if (parent.clipToPadding) {
      top = parent.top + parent.paddingTop
      bottom = parent.bottom - parent.paddingBottom
      left = parent.left + parent.paddingLeft
      right = parent.right - parent.paddingRight
    } else {
      top = parent.top
      bottom = parent.bottom
      left = parent.left
      right = parent.right
    }
    canvas.clipRect(left, top, right, bottom)

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val itemView = parent.getChildAt(i)
      val bounds = Rect()
      parent.getDecoratedBoundsWithMargins(itemView, bounds)
      canvas.drawRect(bounds, paint!!)
    }

  }

  private fun drawGridVertical(canvas: Canvas, parent: RecyclerView) {


    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val itemView = parent.getChildAt(i)
      val bounds = Rect()
      parent.getDecoratedBoundsWithMargins(itemView, bounds)
      canvas.drawRect(bounds, paint!!)

//      val top = bounds.top + itemView.translationY
//      val bottom = bounds.bottom + itemView.translationY
//      val right = bounds.right + itemView.translationX
//      val left = bounds.left + itemView.translationX
//      canvas.drawRect(left, top, right, bottom, paint!!)
    }
  }

  private fun drawGridHorizontal(canvas: Canvas, parent: RecyclerView) {
    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child = parent.getChildAt(i)
      val mBounds = Rect()
      parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)

      val left = child.x
      val right = (mBounds.right + Math.round(child.translationX)).toFloat()
      val bottom = (mBounds.bottom + Math.round(child.translationY)).toFloat()
      val top = child.y
      canvas.drawRect(left, top, right, bottom, paint!!)
    }
  }

}
