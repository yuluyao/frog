package com.capsule.recy.decor;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:10
 */
public class Divider extends RecyclerView.ItemDecoration {

  private int dividerWidth;
  private int dividerColor;

  private Paint paint;

  public static final int LAYOUT_VERTICAL                  = 0;//竖向
  public static final int LAYOUT_HORIZONTAL                = 1;//横向
  public static final int LAYOUT_GRID                      = 2;//表格
  public static final int LAYOUT_STAGGERED_GRID_VERTICAL   = 3;//瀑布--vertical
  public static final int LAYOUT_STAGGERED_GRID_HORIZONTAL = 4;//瀑布--horizontal

  private int layout_type;

  public Divider(int dividerHeight, int dividerColor) {
    this.dividerWidth = dividerHeight;
    this.dividerColor = dividerColor;
    init();
  }

  private void init() {
    paint = new Paint();
    paint.setColor(dividerColor);
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
    switch (layout_type) {
      case LAYOUT_VERTICAL:
        drawVertical(c, parent);
        break;
      case LAYOUT_HORIZONTAL:
        drawHorizontal(c, parent);
        break;
      case LAYOUT_GRID:
        drawGrid(c, parent);
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:
        drawGrid(c, parent);
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:
        drawGrid(c, parent);
        break;
    }
  }

  @SuppressLint("NewApi") private void drawVertical(Canvas canvas, RecyclerView parent) {
    canvas.save();
    final int left;
    final int right;
    if (parent.getClipToPadding()) {
      left = parent.getPaddingLeft();
      right = parent.getWidth() - parent.getPaddingRight();
      canvas.clipRect(left, parent.getPaddingTop(), right,
          parent.getHeight() - parent.getPaddingBottom());
    } else {
      left = 0;
      right = parent.getWidth();
    }

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      Rect mBounds = new Rect();
      parent.getDecoratedBoundsWithMargins(child, mBounds);
      final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
      final int top = bottom - dividerWidth;
      //mDivider.setBounds(left, top, right, bottom);
      //mDivider.draw(canvas);
      canvas.drawRect(left, top, right, bottom, paint);
    }
    canvas.restore();
  }

  @SuppressLint("NewApi") private void drawHorizontal(Canvas canvas, RecyclerView parent) {
    canvas.save();
    final int top;
    final int bottom;
    if (parent.getClipToPadding()) {
      top = parent.getPaddingTop();
      bottom = parent.getHeight() - parent.getPaddingBottom();
      canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(),
          bottom);
    } else {
      top = 0;
      bottom = parent.getHeight();
    }

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      Rect mBounds = new Rect();
      parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
      final int right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
      final int left = right - dividerWidth;
      //mDivider.setBounds(left, top, right, bottom);
      //mDivider.draw(canvas);
      canvas.drawRect(left, top, right, bottom, paint);
    }
    canvas.restore();
  }

  @SuppressLint("NewApi") private void drawGrid(Canvas canvas, RecyclerView parent) {
    drawGridVertical(canvas, parent);
    drawGridHorizontal(canvas, parent);
  }

  private void drawGridVertical(Canvas canvas, RecyclerView parent) {
    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      Rect mBounds = new Rect();
      parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
      float top = child.getY();
      float bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
      float right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
      float left = right - dividerWidth;
      canvas.drawRect(left, top, right, bottom, paint);
    }
  }

  private void drawGridHorizontal(Canvas canvas, RecyclerView parent) {
    int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      Rect mBounds = new Rect();
      parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);

      float left = child.getX();
      float right = left + child.getWidth();
      float bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
      float top = bottom - dividerWidth;
      canvas.drawRect(left, top, right, bottom, paint);
    }
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    readLayoutManager(parent);

    int spanCount = 0;
    switch (layout_type) {
      case LAYOUT_VERTICAL:
        outRect.set(0, 0, 0, dividerWidth);
        return;// 线性 manager 简单处理
      case LAYOUT_HORIZONTAL:
        outRect.set(0, 0, dividerWidth, 0);
        return;// 线性 manager 简单处理
      /* ------------------ */
      case LAYOUT_GRID:
        spanCount = ((GridLayoutManager) parent.getLayoutManager()).getSpanCount();
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:
        spanCount = ((StaggeredGridLayoutManager) parent.getLayoutManager()).getSpanCount();
        break;
    }

    int l = 0;
    int t = 0;
    int r = dividerWidth;
    int b = dividerWidth;
    int childCount = parent.getAdapter().getItemCount();
    int itemPosition = parent.getChildAdapterPosition(view);
    if (isLastRaw(itemPosition, spanCount, childCount)) {
      // 如果是最后一行，则不需要绘制底部
      b = 0;
    }
    if (isLastColumn(itemPosition, spanCount, childCount)) {
      // 如果是最后一列，则不需要绘制右边
      r = 0;
    }
    outRect.set(l, t, r, b);
  }

  /**
   * 判断是否是最后一列
   */
  private boolean isLastColumn(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID:// 如果是最后一列，则不需要绘制右边
        if ((pos + 1) % spanCount == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:// 如果是最后一列，则不需要绘制右边
        if ((pos + 1) % spanCount == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:// 如果是最后一列，则不需要绘制右边
        childCount =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= childCount) {
          return true;
        }
        break;
    }

    return false;
  }

  /**
   * 是否是最后一行
   */
  private boolean isLastRaw(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID: // 如果是最后一行，则不需要绘制底部
        childCount =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= childCount) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL: // 如果是最后一行，则不需要绘制底部
        childCount =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= childCount) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:// 如果是最后一行，则不需要绘制底部
        if ((pos + 1) % spanCount == 0) {
          return true;
        }
        break;
    }
    return false;
  }

  private void readLayoutManager(RecyclerView recyclerView) {
    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      if (layoutManager instanceof GridLayoutManager) {
        //GridLayoutManager
        layout_type = LAYOUT_GRID;
      } else {
        if (((LinearLayoutManager) layoutManager).getOrientation()
            == LinearLayoutManager.VERTICAL) {
          //LinearLayoutManager -- vertical
          layout_type = LAYOUT_VERTICAL;
        } else {
          //LinearLayoutManager -- horizontal
          layout_type = LAYOUT_HORIZONTAL;
        }
      }
    } else {
      if (((StaggeredGridLayoutManager) layoutManager).getOrientation()
          == StaggeredGridLayoutManager.VERTICAL) {
        //StaggeredGridLayoutManager -- vertical
        layout_type = LAYOUT_STAGGERED_GRID_VERTICAL;
      } else {
        //StaggeredGridLayoutManager -- horizontal
        layout_type = LAYOUT_STAGGERED_GRID_HORIZONTAL;
      }
    }
  }
}
