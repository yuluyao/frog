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
public class HeadDecor extends RecyclerView.ItemDecoration {

  /* config */
  private int     layoutRes;//Head 布局
  private boolean sticky;//Head 不随RecyclerView滑动

  private Paint mPaint;

  private int orientation = -1;//RecyclerView 的方向

  private float layoutWidth;
  private float layoutHeight;

  public HeadDecor(int layoutRes) {
    this(layoutRes, false);
  }

  public HeadDecor(int layoutRes, boolean sticky) {
    this.layoutRes = layoutRes;
    this.sticky = sticky;
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setColor(0x6f0000ff);
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
  }

  @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDrawOver(c, parent, state);
    int childCount = parent.getChildCount();
    if (childCount == 0) {
      return;
    }
    ViewGroup itemView = (ViewGroup) parent.getChildAt(0);
    float left;
    float right;
    float top;
    float bottom;
    if (orientation == LinearLayoutManager.VERTICAL) {
      left = parent.getPaddingLeft();
      top = itemView.getY() - layoutHeight;
    } else {
      left = parent.getPaddingLeft() - layoutWidth;
      top = itemView.getY();
    }
    right = parent.getWidth() - parent.getPaddingRight();
    bottom = itemView.getY();
    layoutHead(left, top, right, bottom);

    if (!sticky) {
      if (orientation == LinearLayoutManager.VERTICAL) {
        c.translate(0, top);
      } else {
        c.translate(left, 0);
      }
    }
    header.draw(c);
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    getOrientation(parent);
    measureHead(parent);

    if (parent.getChildAdapterPosition(view) == 0) {
      if (orientation == LinearLayoutManager.VERTICAL) {
        outRect.top = (int) layoutHeight;
      } else {
        outRect.left = (int) layoutWidth;
      }
    }
  }

  private View header;

  private void measureHead(ViewGroup parent) {
    header = LayoutInflater.from(parent.getContext()).inflate(layoutRes, null, false);
    if (header.getLayoutParams() == null) {
      header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
          ViewGroup.LayoutParams.MATCH_PARENT));
    }

    int widthSpec;
    int heightSpec;
    if (orientation == LinearLayoutManager.VERTICAL) {
      widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
      heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.AT_MOST);
    } else {
      widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.AT_MOST);
      heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
    }
    int childWidth =
        ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(),
            header.getLayoutParams().width);
    int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
        parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);

    header.measure(childWidth, childHeight);

    layoutWidth = header.getMeasuredWidth();
    layoutHeight = header.getMeasuredHeight();
  }

  private void getOrientation(RecyclerView parent) {
    if (orientation != -1) {
      return;
    }
    RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      orientation = ((LinearLayoutManager) layoutManager).getOrientation();
    } else {
      throw new RuntimeException("LayoutManager is not instant of LinearLayoutManager!");
    }
  }

  private void layoutHead(float left, float top, float right, float bottom) {
    //header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
    int l = (int) left;
    int t = (int) top;
    int r = (int) right;
    int b = (int) bottom;
    header.layout(l, t, r, b);
  }
}
