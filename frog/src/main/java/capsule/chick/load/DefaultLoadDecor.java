package capsule.chick.load;

import android.support.v7.widget.RecyclerView;
import capsule.chick.R;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/15 14:13
 */
public class DefaultLoadDecor extends SimpleLoadDecor {

  public DefaultLoadDecor(RecyclerView recyclerView) {
    super(recyclerView);
  }

  @Override public int onGetTipLayout() {
    return R.layout.load_tip;
  }

  @Override public int onGetLoadingLayout() {
    return R.layout.load_loading;
  }

  @Override public int onGetFailedLayout() {
    return R.layout.load_failed;
  }

  @Override public int onGetEndLayout() {
    return R.layout.load_end;
  }
}
