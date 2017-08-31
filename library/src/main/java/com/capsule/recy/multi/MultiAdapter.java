package com.capsule.recy.multi;

import com.capsule.recy.BaseAdapter;
import com.capsule.recy.BaseViewHolder;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/29 15:47
 */
public abstract class MultiAdapter<T extends MultiEntity, H extends BaseViewHolder>
    extends BaseAdapter<T, H> {

  protected int getDataItemViewType(int position) {
    T item = getData(position);
    if (null != item) {
      return item.getItemType();
    }
    return -1;
  }
}
