package com.capsule.sample.func.click;

import android.widget.ImageView;
import com.capsule.recy.ViewHolder;
import com.capsule.recy.Adapter;
import com.capsule.sample.R;
import com.capsule.sample.repo.SkillBean;

/**
 * Created by wusheng on 2017/9/2.
 */

public class ClickAdapter extends Adapter<SkillBean, ViewHolder> {

  public ClickAdapter() {
    setItemLayout(R.layout.item_skill_name);
  }

  @Override protected void convert(ViewHolder holder, SkillBean item) {
    ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageResource(item.getIconRes());
    holder.setText(R.id.name, item.getName());
  }
}
