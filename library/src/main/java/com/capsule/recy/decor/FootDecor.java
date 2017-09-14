package com.capsule.recy.decor;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/1 14:31
 */
public class FootDecor extends BaseItemDecoration {
  /* config */
  private int     layoutRes;//Head 布局
  private boolean sticky;//Head 不随RecyclerView滑动

  private Paint mPaint;

  private float layoutWidth;
  private float layoutHeight;

  public FootDecor(int layoutRes) {
    this(layoutRes, false);
  }

  public FootDecor(int layoutRes, boolean sticky) {
    this.layoutRes = layoutRes;
    this.sticky = sticky;
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setColor(0x6f0000ff);
  }

  @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDrawOver(c, parent, state);
    int childCount = parent.getChildCount();
    if (childCount == 0) {
      return;
    }
    int adapterItemCount = parent.getAdapter().getItemCount();
    RecyclerView.ViewHolder holder = parent.findViewHolderForAdapterPosition(adapterItemCount - 1);
    if (holder == null) {
      return;
    }
    ViewGroup itemView = (ViewGroup) holder.itemView;
    float left;
    float right;
    float top;
    float bottom;

    if (layout_type == LAYOUT_VERTICAL) {
      left = parent.getPaddingLeft();
      top = itemView.getY() + itemView.getHeight();
      right = parent.getWidth() - parent.getPaddingRight();
      bottom = top + layoutHeight;
    } else {
      left = itemView.getX() + itemView.getWidth();
      top = parent.getPaddingTop();
      right = left + layoutWidth;
      bottom = parent.getHeight() - parent.getPaddingBottom();
    }
    layoutFoot(left, top, right, bottom);
    c.translate(0, top);
    footer.draw(c);
    c.translate(0, -top);

    //if (!sticky) {
    //  if (orientation == LinearLayoutManager.VERTICAL) {
    //    c.translate(0, top);
    //    footer.draw(c);
    //    c.translate(0, -top);
    //  } else {
    //    c.translate(left, 0);
    //    footer.draw(c);
    //    c.translate(-left, 0);
    //  }
    //} else {
    //  footer.draw(c);
    //}
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    measureFoot(parent);

    if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
      if (layout_type == LAYOUT_VERTICAL) {
        outRect.bottom = (int) layoutHeight;
      } else {
        outRect.right = (int) layoutWidth;
      }
    }
  }

  private View footer;

  private void measureFoot(View recyclerView) {
    footer = LayoutInflater.from(recyclerView.getContext()).inflate(layoutRes, null, false);
    if (footer.getLayoutParams() == null) {
      footer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT));
    }

    int widthSpec;
    int heightSpec;
    if (layout_type == LAYOUT_VERTICAL) {
      widthSpec =
          View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY);
      heightSpec =
          View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), View.MeasureSpec.AT_MOST);
    } else {
      widthSpec =
          View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.AT_MOST);
      heightSpec =
          View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), View.MeasureSpec.EXACTLY);
    }
    int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
        recyclerView.getPaddingLeft() + recyclerView.getPaddingRight(),
        footer.getLayoutParams().width);
    int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
        recyclerView.getPaddingTop() + recyclerView.getPaddingBottom(),
        footer.getLayoutParams().height);

    footer.measure(childWidth, childHeight);

    layoutWidth = footer.getMeasuredWidth();
    layoutHeight = footer.getMeasuredHeight();
  }

  private void layoutFoot(float left, float top, float right, float bottom) {
    footer.layout(((int) left), ((int) top), ((int) right), ((int) bottom));
  }
}
