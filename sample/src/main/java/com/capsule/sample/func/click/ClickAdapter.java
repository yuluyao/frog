package com.capsule.sample.func.click;

import android.widget.ImageView;
import com.capsule.recy.CapAdapter;
import com.capsule.recy.CapViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.SkillBean;

/**
 * Created by wusheng on 2017/9/2.
 */

public class ClickAdapter extends CapAdapter<SkillBean, CapViewHolder> {

  public ClickAdapter() {
    setItemLayout(R.layout.item_skill_name);
  }

  @Override protected void convert(CapViewHolder holder, SkillBean item) {
    ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageResource(item.getIconRes());
    holder.setText(R.id.name, item.getName());
  }
}
