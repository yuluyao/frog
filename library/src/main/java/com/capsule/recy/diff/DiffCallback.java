package com.capsule.recy.diff;

import android.support.v7.util.DiffUtil;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/22 18:32
 */
public  class DiffCallback<T> extends DiffUtil.Callback {

  private List<T> oldData;
  private List<T> newData;

  public DiffCallback(List<T> oldData, List<T> newData) {
    this.oldData = oldData;
    this.newData = newData;
  }

  @Override public int getOldListSize() {
    return oldData.size();
  }

  @Override public int getNewListSize() {
    return newData.size();
  }

  @Override public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
    return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    //return getIdentifyProperty(oldData.get(oldItemPosition)).equals(newData.get(newItemPosition));
  }

  /**
   * 获取item的唯一标识，返回的属性被用来判断item是否相同，如果不同将会触发列表视图变化
   */
  //protected abstract Object getIdentifyProperty(T item);

  @Override public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
    return false;
  }
}
