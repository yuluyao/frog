package com.capsule.trunk.deprecate;

import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import com.capsule.trunk.ViewHolder;
import com.capsule.trunk.Adapter;
import com.capsule.trunk.multi.MultiEntity;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 15:47
 */
public abstract class MultiAdapter<T extends MultiEntity, H extends ViewHolder>
    extends Adapter<T, H> {

  private SparseIntArray typeArray;// viewType and layoutId

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    onSetItemLayout();
  }

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

  protected void setItemLayout(int type, int layoutId) {
    if (typeArray == null) {
      typeArray = new SparseIntArray();
    }
    typeArray.put(type, layoutId);
  }

  /**
   * call {@link #setItemLayout(int, int)}
   */
  protected abstract void onSetItemLayout();

}
