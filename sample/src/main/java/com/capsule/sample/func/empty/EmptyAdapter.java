package com.capsule.sample.func.empty;

import com.capsule.recy.CapAdapter;
import com.capsule.recy.CapViewHolder;
import com.capsule.sample.R;
import com.capsule.sample.repo.SkillBean;

/**
 * Created by wusheng on 2017/9/2.
 */

public class EmptyAdapter extends CapAdapter<SkillBean, CapViewHolder> {

  public EmptyAdapter() {
    setItemLayout(R.layout.item_skill_name);
  }

  @Override protected void convert(CapViewHolder holder, SkillBean item) {

  }
}
