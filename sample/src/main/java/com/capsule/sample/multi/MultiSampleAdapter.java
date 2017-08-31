package com.capsule.sample.multi;

import android.widget.ImageView;
import android.widget.TextView;
import com.capsule.recy.BaseViewHolder;
import com.capsule.recy.multi.MultiAdapter;
import com.capsule.sample.R;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 13:06
 */
public class MultiSampleAdapter extends MultiAdapter<SkillEntity, BaseViewHolder> {

  public MultiSampleAdapter() {
    bindTypeAndLayout(SkillEntity.TYPE_NAME, R.layout.item_skill_name);
    bindTypeAndLayout(SkillEntity.TYPE_DESCRIPTION, R.layout.item_skill_description);
  }

  @Override protected void convert(BaseViewHolder holder, SkillEntity item) {
    switch (item.getItemType()) {
      case SkillEntity.TYPE_NAME:
        ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageResource(item.getIcon());
        ((TextView) holder.itemView.findViewById(R.id.name)).setText(item.getName());
        break;
      case SkillEntity.TYPE_DESCRIPTION:
        ((ImageView) holder.itemView.findViewById(R.id.icon)).setImageResource(item.getIcon());
        ((TextView) holder.itemView.findViewById(R.id.description)).setText(item.getDescription());
        break;
    }
  }
}
