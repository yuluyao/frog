package com.capsule.recy.multi;

import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.capsule.recy.ViewHolder;
import com.capsule.recy.Adapter;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 15:47
 */
public abstract class MultiAdapter<T extends MultiEntity, H extends ViewHolder>
    extends Adapter<T, H> {

  private SparseIntArray typeArray;// viewType and layoutId



  /**
   * call {@link #setType(int, int)}
   */
  protected abstract void onSetTypes();

  @Override public H onCreateViewHolder(ViewGroup parent, int viewType) {
    H holder;
    View itemView = mLayoutInflater.inflate(typeArray.get(viewType), parent, false);
    holder = buildStaticHolder(itemView);
    return holder;
  }

  @Override public int getItemViewType(int position) {
    T item = getData(position);
    if (null != item) {
      return item.getItemType();
    } else {
      return 0;
    }
  }

  protected void setType(int type, int layoutId) {
    if (typeArray == null) {
      typeArray = new SparseIntArray();
    }
    typeArray.put(type, layoutId);
  }

  //will not be called in this subclass
  @Override protected int onGetItemLayoutId() {
    return 0;
  }
}
