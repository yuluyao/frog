package com.capsule.capsuleadapter;

import android.widget.ImageView;
import android.widget.TextView;
import com.capsule.library.BaseAdapter;
import com.capsule.library.BaseViewHolder;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 21:44
 */
public class MainAdapter extends BaseAdapter<SkillBean, BaseViewHolder> {

  public MainAdapter(int mLayoutResId) {
    super(mLayoutResId);
  }

  @Override protected void convert(BaseViewHolder holder, SkillBean item) {
    holder.setText(R.id.tv, item.getName());
    ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageResource(item.getIconRes());
    ((TextView) holder.itemView.findViewById(R.id.icon_id)).setText(String.valueOf(item.getId()));

    holder.setClickableId(R.id.icon);
  }
}
