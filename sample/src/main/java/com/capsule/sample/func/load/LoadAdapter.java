package com.capsule.sample.func.load;

import android.widget.ImageView;
import android.widget.TextView;
import com.capsule.recy.CapAdapter;
import com.capsule.recy.CapViewHolder;
import com.capsule.recy.SaiAdapter;
import com.capsule.sample.R;
import com.capsule.sample.repo.SkillBean;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 21:44
 */
public class LoadAdapter extends SaiAdapter<SkillBean, CapViewHolder> {

  public LoadAdapter() {
    setItemLayout(R.layout.item_normal_list);
  }

  @Override protected void convert(CapViewHolder holder, SkillBean item) {
    holder.setText(R.id.tv, item.getName());
    ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageResource(item.getIconRes());
    ((TextView) holder.itemView.findViewById(R.id.icon_id)).setText(String.valueOf(item.getId()));

    holder.setClickableId(R.id.icon);
  }
}
