package com.capsule.chick.func.load;

import android.content.Context;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/10/20 14:46
 */
public class RLayout extends ViewGroup implements NestedScrollingParent, NestedScrollingChild {

  private IRefreshHead mHead;
  private View mTarget; // the target of the gesture
  private View mFoot;

  private final NestedScrollingParentHelper mNestedScrollingParentHelper;

  private final NestedScrollingChildHelper mNestedScrollingChildHelper;

  public RLayout(Context context) {
    this(context, null);
  }

  public RLayout(Context context, AttributeSet attrs) {
    super(context, attrs);

    // 如果View的子类不打算绘制自己，要调用setWillNotDraw(true)
    // 一般的，ViewGroup的子类不需要绘制自己
    // 这里需要绘制自己，所以调用该方法传入true
    setWillNotDraw(false);
    // 设为按照 getChildDrawingOrder(int, int) 定义的顺序来绘制子View，该方法返回子View的index
    ViewCompat.setChildrenDrawingOrderEnabled(this, true);

    mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);

    //setNestedScrollingEnabled(true);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {

  }

  // nested child

  @Override public void setNestedScrollingEnabled(boolean enabled) {

  }

  @Override public boolean isNestedScrollingEnabled() {
    return false;
  }

  @Override public boolean startNestedScroll(int axes) {
    return false;
  }

  @Override public void stopNestedScroll() {

  }

  @Override public boolean hasNestedScrollingParent() {
    return false;
  }

  @Override public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed, int[] offsetInWindow) {
    return false;
  }

  @Override
  public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
    return false;
  }

  @Override public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
    return false;
  }

  @Override public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
    return false;
  }

  //nested parent

  @Override public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
    return false;
  }

  @Override public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

  }

  @Override public void onStopNestedScroll(View target) {

  }

  @Override
  public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed,
      int dyUnconsumed) {

  }

  @Override public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {

  }

  @Override
  public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
    return false;
  }

  @Override public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
    return false;
  }

  @Override public int getNestedScrollAxes() {
    return 0;
  }



}
