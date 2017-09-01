package com.capsule.sample.decor;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.capsule.sample.R;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 14:51
 */
public class SimpleDecoration extends RecyclerView.ItemDecoration {

  private Context mContext;
  private Paint   mPaint;

  public SimpleDecoration(Context mContext) {
    this.mContext = mContext;
    init();
  }

  private void init() {
    mPaint = new Paint();
    mPaint.setColor(0xffff0000);
    mPaint.setTextSize(48);
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
    int left = parent.getPaddingLeft();
    int right = parent.getWidth() - parent.getPaddingRight();

    for (int i = 0; i < parent.getChildCount(); i++) {
      View itemView = parent.getChildAt(i);
      int top = itemView.getBottom();
      int bottom = itemView.getBottom() + 6;
      c.drawRect(left, top, right, bottom, mPaint);
    }
  }

  public View initHeader(RecyclerView parent) {
    View header = LayoutInflater.from(mContext).inflate(R.layout.item_skill_name, parent, false);
    if (header.getLayoutParams() == null) {
      header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    int widthSpec;
    int heightSpec;

    //if (mOrientation == LinearLayoutManager.VERTICAL) {
    widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
    heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.UNSPECIFIED);
    //} else {
    //  widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.UNSPECIFIED);
    //  heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
    //}

    int childWidth =
        ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(),
            header.getLayoutParams().width);
    int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
        parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);

    header.measure(childWidth, childHeight);
    header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
    return header;
  }

  @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDrawOver(c, parent, state);
    View layout = initHeader(parent);
    layout.draw(c);
    //for (int i = 0; i < parent.getChildCount(); i++) {
    //  View child = parent.getChildAt(i);
    //  float left = child.getX();
    //  float top = child.getY();
    //  float right = child.getWidth();
    //  float bottom = child.getHeight();
    //
    //  View layout = LayoutInflater.from(mContext).inflate(R.layout.item_skill_name, parent, false);
    //  layout.draw(c);
    //}

  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    outRect.bottom = 6;
  }
}
