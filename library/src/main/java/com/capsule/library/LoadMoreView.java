package com.capsule.library;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

/**
 * Created by BlingBling on 2016/11/11.
 */

public abstract class LoadMoreView {

  public static final int     STATUS_DEFAULT   = 1;
  public static final int     STATUS_LOADING   = 2;
  public static final int     STATUS_FAIL      = 3;
  public static final int     STATUS_END       = 4;

  private             int     mLoadMoreStatus  = STATUS_DEFAULT;
  private             boolean mLoadMoreEndGone = false;



  public void convert(BaseViewHolder holder) {
    switch (mLoadMoreStatus) {
      case STATUS_LOADING:
        visibleLoading(holder, true);
        visibleLoadFail(holder, false);
        visibleLoadEnd(holder, false);
        break;
      case STATUS_FAIL:
        visibleLoading(holder, false);
        visibleLoadFail(holder, true);
        visibleLoadEnd(holder, false);
        break;
      case STATUS_END:
        visibleLoading(holder, false);
        visibleLoadFail(holder, false);
        visibleLoadEnd(holder, true);
        break;
      case STATUS_DEFAULT:
        visibleLoading(holder, false);
        visibleLoadFail(holder, false);
        visibleLoadEnd(holder, false);
        break;
    }
  }



  private void visibleLoading(BaseViewHolder holder, boolean visible) {
    holder.setVisibility(getLoadingViewId(), visible);
  }

  private void visibleLoadFail(BaseViewHolder holder, boolean visible) {
    holder.setVisibility(getLoadFailViewId(), visible);
  }

  private void visibleLoadEnd(BaseViewHolder holder, boolean visible) {
    final int loadEndViewId = getLoadEndViewId();
    if (loadEndViewId != 0) {
      holder.setVisibility(loadEndViewId, visible);
    }
  }

  public void setLoadMoreStatus(int loadMoreStatus) {
    this.mLoadMoreStatus = loadMoreStatus;
  }

  public int getLoadMoreStatus() {
    return mLoadMoreStatus;
  }

  public final void setLoadMoreEndGone(boolean loadMoreEndGone) {
    this.mLoadMoreEndGone = loadMoreEndGone;
  }

  public final boolean isLoadEndMoreGone() {
    if (getLoadEndViewId() == 0) {
      return true;
    }
    return mLoadMoreEndGone;
  }

  public boolean isLoading(){
    return mLoadMoreStatus == STATUS_LOADING;
  }


  public abstract @LayoutRes int getLayoutId();

  protected abstract @IdRes int getLoadingViewId();

  protected abstract @IdRes int getLoadFailViewId();

  protected abstract @IdRes int getLoadEndViewId();
}
