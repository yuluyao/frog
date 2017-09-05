package com.capsule.recy.decor;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

  public static final int LAYOUT_VERTICAL       = 0;//竖向
  public static final int LAYOUT_HORIZONTAL     = 1;//横向
  public static final int LAYOUT_GRID           = 2;//表格
  public static final int LAYOUT_STAGGERED_GRID = 3;//瀑布

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

        break;
      case LAYOUT_STAGGERED_GRID:

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

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    readLayoutManager(parent);
    switch (layout_type) {
      case LAYOUT_VERTICAL:
        outRect.set(0, 0, 0, dividerWidth);
        break;
      case LAYOUT_HORIZONTAL:
        outRect.set(0, 0, dividerWidth, 0);
        break;
      case LAYOUT_GRID:

        break;
      case LAYOUT_STAGGERED_GRID:

        break;
    }
  }

  private void readLayoutManager(RecyclerView recyclerView) {
    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      if (layoutManager instanceof GridLayoutManager) {
        //GridLayoutManager
        layout_type = LAYOUT_GRID;
      } else {
        //LinearLayoutManager
        if (((LinearLayoutManager) layoutManager).getOrientation()
            == LinearLayoutManager.VERTICAL) {
          layout_type = LAYOUT_VERTICAL;
        } else {
          layout_type = LAYOUT_HORIZONTAL;
        }
      }
    } else {
      //StaggeredGridLayoutManager
      layout_type = LAYOUT_STAGGERED_GRID;
    }
  }
}
