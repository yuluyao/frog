package com.capsule.library;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by BlingBling on 2016/11/11.
 */

public abstract class LoadMoreView {

  public static final int LOADING = 2;
  public static final int FAILED  = 3;
  public static final int END     = 4;
  public static final int IDLE    = 1;

  private View view_loading;
  private View view_failed;
  private View view_end;

  private int mStatus = IDLE;

  public void convert(BaseViewHolder holder) {
    view_loading = holder.itemView.findViewById(getLoadingViewId());
    view_failed = holder.itemView.findViewById(getLoadFailViewId());
    view_end = holder.itemView.findViewById(getLoadEndViewId());
    handleStatus();
  }

  public boolean isLoading() {
    return mStatus == LOADING;
  }

  public void setStatus(int status) {
    mStatus = status;
    handleStatus();
  }

  private void handleStatus() {
    switch (mStatus) {
      case LOADING:
        showLoading();
        break;
      case FAILED:
        showFail();
        break;
      case END:
        showEnd();
        break;
      case IDLE:
        showIdle();
        break;
    }
  }

  private void showLoading() {
    view_loading.setVisibility(View.VISIBLE);
    view_failed.setVisibility(View.INVISIBLE);
    view_end.setVisibility(View.INVISIBLE);
  }

  private void showFail() {
    view_loading.setVisibility(View.INVISIBLE);
    view_failed.setVisibility(View.VISIBLE);
    view_end.setVisibility(View.INVISIBLE);
  }

  private void showEnd() {
    view_loading.setVisibility(View.INVISIBLE);
    view_failed.setVisibility(View.INVISIBLE);
    view_end.setVisibility(View.VISIBLE);
  }

  private void showIdle() {
    view_loading.setVisibility(View.INVISIBLE);
    view_failed.setVisibility(View.INVISIBLE);
    view_end.setVisibility(View.INVISIBLE);
  }

  public abstract @LayoutRes int getLayoutId();

  protected abstract @IdRes int getLoadingViewId();

  protected abstract @IdRes int getLoadFailViewId();

  protected abstract @IdRes int getLoadEndViewId();
}
