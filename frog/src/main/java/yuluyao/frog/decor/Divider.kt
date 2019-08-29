package yuluyao.frog.decor

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:10
 */
class Divider(private val width: Float = 0F,
              private val colorRes:Int ) : BaseItemDecoration() {
  private var widthPixels: Float = 0.toFloat()
  private var paint: Paint? = null


//  constructor(private val width:Float= 0F)

  init {
    paint = Paint()
//    //init divider width and color
//    widthPixels = (Resources.getSystem().displayMetrics.density * width)
//    paint!!.color = Resources.getSystem().getColor(colorRes)
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
    val b = widthPixels / 2
    val childCount = parent.adapter!!.itemCount
    val itemPosition = parent.getChildAdapterPosition(view)
    if (isFirstRaw2(itemPosition, spanCount, childCount)) {
      // 如果是第一行，则不需要绘制上边
      t = 0f
    }
    //    if (isLastRaw2(itemPosition, spanCount, childCount)) {
    //      // 如果是最后一行，则不需要绘制底部
    //      b = 0;
    //    }
    if (isFirstColumn2(itemPosition, spanCount, childCount)) {
      // 如果是第一列，则不需要绘制左边
      l = 0f
    }
    if (isLastColumn2(itemPosition, spanCount, childCount)) {
      // 如果是最后一列，则不需要绘制右边
      r = 0f
    }
    outRect.set(l.toInt(), t.toInt(), r.toInt(), b.toInt())
  }




  private fun isFirstRaw2(pos: Int, spanCount: Int, childCount: Int): Boolean {
    when (layout_type) {
      LAYOUT_GRID -> {
        val sizeLookup = (mRecyclerView!!.layoutManager as GridLayoutManager).spanSizeLookup
        val spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount)
        val spanIndex = sizeLookup.getSpanIndex(pos, spanCount)
        val spanSize = sizeLookup.getSpanSize(pos)

        if (spanGroupIndex == 0) {
          return true
        }
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


  private fun isFirstColumn2(pos: Int, spanCount: Int, childCount: Int): Boolean {
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

  private fun isLastRaw2(pos: Int, spanCount: Int, childCount: Int): Boolean {
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


  private fun isLastColumn2(pos: Int, spanCount: Int, childCount: Int): Boolean {
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
    val left: Float
    val right: Float
    //if (parent.getClipToPadding()) {
    left = parent.paddingLeft.toFloat()
    right = (parent.width - parent.paddingRight).toFloat()
    canvas.clipRect(left, parent.paddingTop.toFloat(), right,
      (parent.height - parent.paddingBottom).toFloat())
    //} else {
    //  left = 0;
    //  right = parent.getWidth();
    //}

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child = parent.getChildAt(i)
      val mBounds = Rect()
      parent.getDecoratedBoundsWithMargins(child, mBounds)
      val bottom = (mBounds.bottom + Math.round(ViewCompat.getTranslationY(child))).toFloat()
      val top = bottom - widthPixels
      //mDivider.setBounds(left, top, right, bottom);
      //mDivider.draw(canvas);
      canvas.drawRect(left, top, right, bottom, paint!!)
    }
    canvas.restore()
  }

  private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
    canvas.save()
    val top: Float
    val bottom: Float
    //if (parent.getClipToPadding()) {
    top = parent.paddingTop.toFloat()
    bottom = (parent.height - parent.paddingBottom).toFloat()
    canvas.clipRect(parent.paddingLeft.toFloat(), top,
      (parent.width - parent.paddingRight).toFloat(),
      bottom)
    //} else {
    //  top = 0;
    //  bottom = parent.getHeight();
    //}

    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child = parent.getChildAt(i)
      val mBounds = Rect()
      parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)
      val right = (mBounds.right + Math.round(ViewCompat.getTranslationX(child))).toFloat()
      val left = right - widthPixels
      //mDivider.setBounds(left, top, right, bottom);
      //mDivider.draw(canvas);
      canvas.drawRect(left, top, right, bottom, paint!!)
    }
    canvas.restore()
  }

  @SuppressLint("NewApi")
  private fun drawGrid(canvas: Canvas, parent: RecyclerView) {
    drawGridVertical(canvas, parent)
    //    drawGridHorizontal(canvas, parent);
  }

  private fun drawGridVertical(canvas: Canvas, parent: RecyclerView) {
    val childCount = parent.childCount
    for (i in 0 until childCount) {
      val child = parent.getChildAt(i)
      val mBounds = Rect()
      parent.layoutManager!!.getDecoratedBoundsWithMargins(child, mBounds)

      val top = (mBounds.top + Math.round(child.translationY)).toFloat()
      val bottom = (mBounds.bottom + Math.round(child.translationY)).toFloat()
      val right = (mBounds.right + Math.round(child.translationX)).toFloat()
      val left = (mBounds.left + Math.round(child.translationX)).toFloat()
      canvas.drawRect(left, top, right, bottom, paint!!)
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
