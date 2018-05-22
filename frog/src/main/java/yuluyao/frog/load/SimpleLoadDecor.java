package yuluyao.frog.load;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import yuluyao.frog.decor.FootDecor;

import static android.support.v7.widget.RecyclerView.ItemDecoration;

/**
 * Created by wusheng on 2017/9/3.
 */

public abstract class SimpleLoadDecor extends BaseLoadDecor {

  public SimpleLoadDecor(RecyclerView recyclerView) {
    super(recyclerView);
  }

  @Override public ItemDecoration onGetTipDecor() {
    return new FootDecor(onGetTipLayout());
  }

  @Override public ItemDecoration onGetLoadingDecor() {
    return new FootDecor(onGetLoadingLayout());
  }

  @Override public ItemDecoration onGetFailedDecor() {
    return new FootDecor(onGetFailedLayout());
  }

  @Override public ItemDecoration onGetEndDecor() {
    return new FootDecor(onGetEndLayout());
  }

  public abstract @LayoutRes int onGetTipLayout();

  public abstract @LayoutRes int onGetLoadingLayout();

  public abstract @LayoutRes int onGetFailedLayout();

  public abstract @LayoutRes int onGetEndLayout();
}
