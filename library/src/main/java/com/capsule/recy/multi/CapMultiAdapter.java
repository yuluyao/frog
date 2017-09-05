package com.capsule.recy.multi;

import com.capsule.recy.ViewHolder;
import com.capsule.recy.Adapter;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 15:47
 */
public abstract class CapMultiAdapter<T extends MultiEntity, H extends ViewHolder>
    extends Adapter<T, H> {

  protected int getDataItemViewType(int position) {
    T item = getData(position);
    if (null != item) {
      return item.getItemType();
    }
    return -1;
  }
}
