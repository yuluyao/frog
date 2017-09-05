package com.capsule.sample.func.empty;

import com.capsule.recy.ViewHolder;
import com.capsule.recy.Adapter;
import com.capsule.sample.R;
import com.capsule.sample.repo.SkillBean;

/**
 * Created by wusheng on 2017/9/2.
 */

public class EmptyAdapter extends Adapter<SkillBean, ViewHolder> {

  public EmptyAdapter() {
    setItemLayout(R.layout.item_skill_name);
  }

  @Override protected void convert(ViewHolder holder, SkillBean item) {

  }
}
