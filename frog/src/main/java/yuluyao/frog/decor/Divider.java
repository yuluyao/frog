package yuluyao.frog.decor;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.ColorRes;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:10
 */
public class Divider extends BaseItemDecoration {

  private int colorRes;
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

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
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

  @SuppressLint("NewApi")
  private void drawGrid(Canvas canvas, RecyclerView parent) {
    drawGridVertical(canvas, parent);
//    drawGridHorizontal(canvas, parent);
  }

  private void drawGridVertical(Canvas canvas, RecyclerView parent) {
    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      Rect mBounds = new Rect();
      parent.getLayoutManager().getDecoratedBoundsWithMargins(child, mBounds);

      float top = mBounds.top + Math.round(child.getTranslationY());
      float bottom = mBounds.bottom + Math.round(child.getTranslationY());
      float right = mBounds.right + Math.round(child.getTranslationX());
      float left = mBounds.left + Math.round(child.getTranslationX());
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
      float right = mBounds.right + Math.round(child.getTranslationX());
      float bottom = mBounds.bottom + Math.round(child.getTranslationY());
      float top = child.getY();
      canvas.drawRect(left, top, right, bottom, paint);
    }
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
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

    float l = dividerWidthPixel / 2;
    float t = dividerWidthPixel / 2;
    float r = dividerWidthPixel / 2;
    float b = dividerWidthPixel / 2;
    int childCount = parent.getAdapter().getItemCount();
    int itemPosition = parent.getChildAdapterPosition(view);
    if (isFirstRaw2(itemPosition, spanCount, childCount)) {
      // 如果是第一行，则不需要绘制上边
      t = 0;
    }
//    if (isLastRaw2(itemPosition, spanCount, childCount)) {
//      // 如果是最后一行，则不需要绘制底部
//      b = 0;
//    }
    if (isFirstColumn2(itemPosition, spanCount, childCount)) {
      // 如果是第一列，则不需要绘制左边
      l = 0;
    }
    if (isLastColumn2(itemPosition, spanCount, childCount)) {
      // 如果是最后一列，则不需要绘制右边
      r = 0;
    }
    outRect.set(((int) l), ((int) t), ((int) r), ((int) b));
  }


  private boolean isFirstColumn(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID:
        if ((pos) % spanCount == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:
        if ((pos) % spanCount == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:
        int result =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result) {
          return true;
        }
        break;
    }

    return false;
  }

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
        int result =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result) {
          return true;
        }
        break;
    }

    return false;
  }


  private boolean isFirstRaw(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID:
        if (pos < spanCount) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:
        int result2 =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result2) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:
        if ((pos + 1) % spanCount == 0) {
          return true;
        }
        break;
    }
    return false;
  }


  private boolean isLastRaw(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID: // 如果是最后一行，则不需要绘制底部
        int result =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL: // 如果是最后一行，则不需要绘制底部
        int result2 =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result2) {
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


  private boolean isFirstRaw2(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID:
        GridLayoutManager.SpanSizeLookup sizeLookup = ((GridLayoutManager) recyclerView
            .getLayoutManager()).getSpanSizeLookup();
        int spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount);
        int spanIndex = sizeLookup.getSpanIndex(pos, spanCount);
        int spanSize = sizeLookup.getSpanSize(pos);

        if (spanGroupIndex == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:
        int result2 =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result2) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:
        if ((pos + 1) % spanCount == 0) {
          return true;
        }
        break;
    }
    return false;
  }


  private boolean isFirstColumn2(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID:
        GridLayoutManager.SpanSizeLookup sizeLookup = ((GridLayoutManager) recyclerView
            .getLayoutManager()).getSpanSizeLookup();
        int spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount);
        int spanIndex = sizeLookup.getSpanIndex(pos, spanCount);
        int spanSize = sizeLookup.getSpanSize(pos);

        if (spanIndex == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:
        if ((pos) % spanCount == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:
        int result =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result) {
          return true;
        }
        break;
    }

    return false;
  }

  private boolean isLastRaw2(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID: // 如果是最后一行，则不需要绘制底部
        GridLayoutManager.SpanSizeLookup sizeLookup = ((GridLayoutManager) recyclerView
            .getLayoutManager()).getSpanSizeLookup();
        int spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount);
        int spanIndex = sizeLookup.getSpanIndex(pos, spanCount);
        int spanSize = sizeLookup.getSpanSize(pos);

        int result =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL: // 如果是最后一行，则不需要绘制底部
        int result2 =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result2) {
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


  private boolean isLastColumn2(int pos, int spanCount, int childCount) {
    switch (layout_type) {
      case LAYOUT_GRID:// 如果是最后一列，则不需要绘制右边
        GridLayoutManager.SpanSizeLookup sizeLookup = ((GridLayoutManager) recyclerView
            .getLayoutManager()).getSpanSizeLookup();
        int spanGroupIndex = sizeLookup.getSpanGroupIndex(pos, spanCount);
        int spanIndex = sizeLookup.getSpanIndex(pos, spanCount);
        int spanSize = sizeLookup.getSpanSize(pos);

       if (spanIndex + spanSize -1 == spanCount-1){
         return true;
       }

//        if ((pos + 1) % spanCount == 0) {
//          return true;
//        }
        break;
      case LAYOUT_STAGGERED_GRID_VERTICAL:// 如果是最后一列，则不需要绘制右边
        if ((pos + 1) % spanCount == 0) {
          return true;
        }
        break;
      case LAYOUT_STAGGERED_GRID_HORIZONTAL:// 如果是最后一列，则不需要绘制右边
        int result =
            childCount - (childCount % spanCount == 0 ? spanCount : childCount % spanCount);
        if (pos >= result) {
          return true;
        }
        break;
    }

    return false;
  }


}
