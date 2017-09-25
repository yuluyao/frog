package com.capsule.trunk.decor;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:10
 */
public class Divider extends BaseItemDecoration {

  private int   colorRes;
  private float dividerWidthDp;
  private float dividerWidthPixel;
  private Paint paint;

  public Divider(float dividerWidthDp, @ColorRes int colorRes) {
    this.dividerWidthDp = dividerWidthDp;
    this.colorRes = colorRes;
    init();
  }

  private void init() {
    paint = new Paint();
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

  private void drawVertical(Canvas canvas, RecyclerView parent) {
    canvas.save();
    final float left;
    final float right;
    //if (parent.getClipToPadding()) {
    left = parent.getPaddingLeft();
    right = parent.getWidth() - parent.getPaddingRight();
    canvas.clipRect(left, parent.getPaddingTop(), right,
        parent.getHeight() - parent.getPaddingBottom());
    //} else {
    //  left = 0;
    //  right = parent.getWidth();
    //}

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      Rect mBounds = new Rect();
      parent.getDecoratedBoundsWithMargins(child, mBounds);
      final float bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
      final float top = bottom - dividerWidthPixel;
      //mDivider.setBounds(left, top, right, bottom);
      //mDivider.draw(canvas);
      canvas.drawRect(left, top, right, bottom, paint);
    }
    canvas.restore();
  }

  private void drawHorizontal(Canvas canvas, RecyclerView parent) {
    canvas.save();
    final float top;
    final float bottom;
    //if (parent.getClipToPadding()) {
    top = parent.getPaddingTop();
    bottom = parent.getHeight() - parent.getPaddingBottom();
    canvas.clipRect(parent.getPaddingLeft(), top, parent.getWidth() - parent.getPaddingRight(),
        bottom);
    //} else {
    //  top = 0;
    //  bottom = parent.getHeight();
    //}

    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      Rect mBounds = new Rect();
      parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);
      final float right = mBounds.right + Math.round(ViewCompat.getTranslationX(child));
      final float left = right - dividerWidthPixel;
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
      float left = right - dividerWidthPixel;
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
      float top = bottom - dividerWidthPixel;
      canvas.drawRect(left, top, right, bottom, paint);
    }
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    if (layout_type != LAYOUT_INVALID) {
      //init divider width and color
      dividerWidthPixel = (int) (parent.getContext().getResources().getDisplayMetrics().density
          * dividerWidthDp);
      paint.setColor(parent.getContext().getResources().getColor(colorRes));
    }

    int spanCount = 0;
    switch (layout_type) {
      case LAYOUT_VERTICAL:
        outRect.set(0, 0, 0, (int) dividerWidthPixel);
        return;// 线性 manager 简单处理
      case LAYOUT_HORIZONTAL:
        outRect.set(0, 0, (int) dividerWidthPixel, 0);
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

    float l = 0;
    float t = 0;
    float r = dividerWidthPixel;
    float b = dividerWidthPixel;
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
    outRect.set(((int) l), ((int) t), ((int) r), ((int) b));
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
}
