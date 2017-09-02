package com.capsule.recy.load;

import android.support.v7.widget.RecyclerView;
import com.capsule.recy.R;
import com.capsule.recy.decor.FootDecor;

import static android.support.v7.widget.RecyclerView.*;

/**
 * Created by wusheng on 2017/9/3.
 */

public class Load {

  public static final int LOAD_STATE_ABLE    = 1;
  public static final int LOAD_STATE_BEGIN   = 2;
  public static final int LOAD_STATE_LOADING = 3;
  public static final int LOAD_STATE_FAILED  = 4;
  public static final int LOAD_STATE_END     = 4;

  private int state = LOAD_STATE_ABLE;

  private ItemDecoration tip;
  private ItemDecoration loading;
  private ItemDecoration failed;
  private ItemDecoration end;

  private RecyclerView recyclerView;

  public Load(RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
    tip = new FootDecor(R.layout.load_tip);
    loading = new FootDecor(R.layout.load_loading);
    failed = new FootDecor(R.layout.load_failed);
    end = new FootDecor(R.layout.load_end);
  }

  public boolean isAble() {
    return state == LOAD_STATE_ABLE;
  }

  public boolean isBegin() {
    return state == LOAD_STATE_BEGIN;
  }

  public boolean isLoading() {
    return state == LOAD_STATE_LOADING;
  }

  public boolean isFailed() {
    return state == LOAD_STATE_FAILED;
  }

  public boolean isEnd() {
    return state == LOAD_STATE_END;
  }

  public void setAble() {
    state = LOAD_STATE_ABLE;
    removeItemDecorations();
    recyclerView.addItemDecoration(tip);
  }

  public void setBegin() {
    state = LOAD_STATE_BEGIN;
    removeItemDecorations();
    recyclerView.addItemDecoration(tip);
  }

  public void setLoading() {
    state = LOAD_STATE_LOADING;
    removeItemDecorations();
    recyclerView.addItemDecoration(loading);
  }

  public void setFailed() {
    state = LOAD_STATE_FAILED;
    removeItemDecorations();
    recyclerView.addItemDecoration(failed);
  }

  public void setEnd() {
    state = LOAD_STATE_END;
    removeItemDecorations();
    recyclerView.addItemDecoration(end);
  }

  private void removeItemDecorations() {
    recyclerView.removeItemDecoration(tip);
    recyclerView.removeItemDecoration(loading);
    recyclerView.removeItemDecoration(failed);
    recyclerView.removeItemDecoration(end);
  }

}
