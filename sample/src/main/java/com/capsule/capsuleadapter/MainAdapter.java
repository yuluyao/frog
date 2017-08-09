package com.capsule.capsuleadapter;

import com.capsule.library.BaseAdapter;
import com.capsule.library.BaseViewHolder;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 21:44
 */
public class MainAdapter extends BaseAdapter<String,BaseViewHolder> {

  public MainAdapter(List<String> mData, int mLayoutResId) {
    super(mData, mLayoutResId);
  }

  @Override protected void convert(BaseViewHolder holder, String item) {
    holder.setText(R.id.tv, item);
  }
}
