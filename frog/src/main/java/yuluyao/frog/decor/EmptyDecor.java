package yuluyao.frog.decor;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/1 14:30
 */
public class EmptyDecor extends BaseItemDecoration {

  private int layoutRes;

  public EmptyDecor(int layoutRes) {
    this.layoutRes = layoutRes;
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
    int childCount = parent.getChildCount();
    if (childCount == 0) {
      View empty = initLayoutResource(parent);
      empty.draw(c);
    }
  }

  public View initLayoutResource(RecyclerView parent) {
    View header = LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    if (header.getLayoutParams() == null) {
      header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
          ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    int widthSpec;
    int heightSpec;
    //int orientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
    if (layout_type == LAYOUT_VERTICAL) {
      widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
      heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
    } else {
      widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(), View.MeasureSpec.EXACTLY);
      heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(), View.MeasureSpec.EXACTLY);
    }
    int childWidth =
        ViewGroup.getChildMeasureSpec(widthSpec, parent.getPaddingLeft() + parent.getPaddingRight(),
            header.getLayoutParams().width);
    int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
        parent.getPaddingTop() + parent.getPaddingBottom(), header.getLayoutParams().height);

    header.measure(childWidth, childHeight);
    header.layout(0, 0, header.getMeasuredWidth(), header.getMeasuredHeight());
    return header;
  }
}
