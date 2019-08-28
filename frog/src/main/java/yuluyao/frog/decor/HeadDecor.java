//package yuluyao.frog.decor;
//
//import android.graphics.Canvas;
//import android.graphics.Paint;
//import android.graphics.Rect;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
///**
// * 描 述：
// * 作 者：Vegeta Yu
// * 时 间：2017/9/1 14:31
// */
//public class HeadDecor extends BaseItemDecoration {
//
//  /* config */
//  private int     layoutRes;//Head 布局
//  private boolean sticky;//Head 不随RecyclerView滑动
//
//  private Paint mPaint;
//
//  private float layoutWidth;
//  private float layoutHeight;
//
//  public HeadDecor(int layoutRes) {
//    this(layoutRes, false);
//  }
//
//  public HeadDecor(int layoutRes, boolean sticky) {
//    this.layoutRes = layoutRes;
//    this.sticky = sticky;
//    init();
//  }
//
//  private void init() {
//    mPaint = new Paint();
//    mPaint.setColor(0x6f0000ff);
//  }
//
//  @Override public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
//    super.onDrawOver(c, parent, state);
//    int childCount = parent.getChildCount();
//    if (childCount == 0) {
//      return;
//    }
//    ViewGroup itemView = (ViewGroup) parent.getChildAt(0);
//    float left;
//    float right;
//    float top;
//    float bottom;
//    if (layout_type == LAYOUT_VERTICAL) {
//      left = parent.getPaddingLeft();
//      top = itemView.getY() - layoutHeight;
//    } else {
//      left = parent.getPaddingLeft() - layoutWidth;
//      top = itemView.getY();
//    }
//    right = parent.getWidth() - parent.getPaddingRight();
//    bottom = itemView.getY();
//    layoutHead(left, top, right, bottom);
//
//    if (!sticky) {
//      if (layout_type == LAYOUT_VERTICAL) {
//        c.translate(0, top);
//        header.draw(c);
//        c.translate(0, -top);
//      } else {
//        c.translate(left, 0);
//        header.draw(c);
//        c.translate(-left, 0);
//      }
//    } else {
//      header.draw(c);
//    }
//  }
//
//  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
//      RecyclerView.State state) {
//    super.getItemOffsets(outRect, view, parent, state);
//    measureHead(parent);
//
//    if (parent.getChildAdapterPosition(view) == 0) {
//      if (layout_type == LAYOUT_VERTICAL) {
//        outRect.top = (int) layoutHeight;
//      } else {
//        outRect.left = (int) layoutWidth;
//      }
//    }
//  }
//
//  private View header;
//
//  private void measureHead(View recyclerView) {
//    header = LayoutInflater.from(recyclerView.getContext()).inflate(layoutRes, null, false);
//    if (header.getLayoutParams() == null) {
//      header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//          ViewGroup.LayoutParams.MATCH_PARENT));
//    }
//
//    int widthSpec;
//    int heightSpec;
//    if (layout_type == LAYOUT_VERTICAL) {
//      widthSpec =
//          View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY);
//      heightSpec =
//          View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), View.MeasureSpec.AT_MOST);
//    } else {
//      widthSpec =
//          View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.AT_MOST);
//      heightSpec =
//          View.MeasureSpec.makeMeasureSpec(recyclerView.getHeight(), View.MeasureSpec.EXACTLY);
//    }
//    int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
//        recyclerView.getPaddingLeft() + recyclerView.getPaddingRight(),
//        header.getLayoutParams().width);
//    int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
//        recyclerView.getPaddingTop() + recyclerView.getPaddingBottom(),
//        header.getLayoutParams().height);
//
//    header.measure(childWidth, childHeight);
//
//    layoutWidth = header.getMeasuredWidth();
//    layoutHeight = header.getMeasuredHeight();
//  }
//
//
//  private void layoutHead(float left, float top, float right, float bottom) {
//    header.layout(((int) left), ((int) top), ((int) right), ((int) bottom));
//  }
//}
