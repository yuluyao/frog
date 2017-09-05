package com.capsule.sample.func.head;

import android.widget.ImageView;
import com.capsule.recy.ViewHolder;
import com.capsule.recy.Adapter;
import com.capsule.sample.R;
import com.capsule.sample.repo.SkillBean;

/**
 * Created by wusheng on 2017/9/2.
 */

public class HeadAdapter extends Adapter<SkillBean, ViewHolder> {

  public HeadAdapter() {
    setItemLayout(R.layout.item_skill_name);
  }

  @Override protected void convert(ViewHolder holder, SkillBean item) {
    ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageResource(item.getIconRes());
    holder.setText(R.id.name, item.getName());
  }
}
