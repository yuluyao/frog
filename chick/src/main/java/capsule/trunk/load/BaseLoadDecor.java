package capsule.trunk.load;

import android.support.v7.widget.RecyclerView;

import static android.support.v7.widget.RecyclerView.ItemDecoration;

/**
 * Created by wusheng on 2017/9/3.
 */

public abstract class BaseLoadDecor {

  private static final int LOAD_STATE_ABLE    = 1;
  private static final int LOAD_STATE_BEGIN   = 2;
  private static final int LOAD_STATE_LOADING = 3;
  private static final int LOAD_STATE_FAILED  = 4;
  private static final int LOAD_STATE_END     = 5;

  private int state = LOAD_STATE_ABLE;

  private ItemDecoration tipDecor;
  private ItemDecoration loadingDecor;
  private ItemDecoration failedDecor;
  private ItemDecoration endDecor;

  private RecyclerView recyclerView;

  public BaseLoadDecor(RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
    tipDecor = onGetTipDecor();
    loadingDecor = onGetLoadingDecor();
    failedDecor = onGetFailedDecor();
    endDecor = onGetEndDecor();
  }

  public abstract ItemDecoration onGetTipDecor();

  public abstract ItemDecoration onGetLoadingDecor();

  public abstract ItemDecoration onGetFailedDecor();

  public abstract ItemDecoration onGetEndDecor();

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
    recyclerView.addItemDecoration(tipDecor);
  }

  public void setBegin() {
    state = LOAD_STATE_BEGIN;
    removeItemDecorations();
    recyclerView.addItemDecoration(tipDecor);
  }

  public void setLoading() {
    state = LOAD_STATE_LOADING;
    removeItemDecorations();
    recyclerView.addItemDecoration(loadingDecor);
  }

  public void setFailed() {
    state = LOAD_STATE_FAILED;
    removeItemDecorations();
    recyclerView.addItemDecoration(failedDecor);
  }

  public void setEnd() {
    state = LOAD_STATE_END;
    removeItemDecorations();
    recyclerView.addItemDecoration(endDecor);
  }

  private void removeItemDecorations() {
    recyclerView.removeItemDecoration(tipDecor);
    recyclerView.removeItemDecoration(loadingDecor);
    recyclerView.removeItemDecoration(failedDecor);
    recyclerView.removeItemDecoration(endDecor);
  }
}
