package com.capsule.capsuleadapter;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/10 14:16
 */
public class DataRepo {

  private static List<SkillBean> data = new ArrayList<>();

  static {
    data.add(new SkillBean(R.drawable.mh_1));
    data.add(new SkillBean(R.drawable.mh_2));
    data.add(new SkillBean(R.drawable.mh_3));
    data.add(new SkillBean(R.drawable.mh_4));
    data.add(new SkillBean(R.drawable.mh_5));
    data.add(new SkillBean(R.drawable.mh_6));
    data.add(new SkillBean(R.drawable.mh_7));
    data.add(new SkillBean(R.drawable.mh_8));
    data.add(new SkillBean(R.drawable.mh_9));
    data.add(new SkillBean(R.drawable.mh_10));
    data.add(new SkillBean(R.drawable.mh_11));
    data.add(new SkillBean(R.drawable.mh_12));
    data.add(new SkillBean(R.drawable.mh_13));
    data.add(new SkillBean(R.drawable.mh_14));
    data.add(new SkillBean(R.drawable.mh_15));
    data.add(new SkillBean(R.drawable.mh_16));
    data.add(new SkillBean(R.drawable.mh_17));
    data.add(new SkillBean(R.drawable.mh_18));
    data.add(new SkillBean(R.drawable.mh_19));
    data.add(new SkillBean(R.drawable.mh_20));
    data.add(new SkillBean(R.drawable.mh_21));
    data.add(new SkillBean(R.drawable.mh_22));
    data.add(new SkillBean(R.drawable.mh_23));
    data.add(new SkillBean(R.drawable.mh_24));
    data.add(new SkillBean(R.drawable.mh_25));
    data.add(new SkillBean(R.drawable.mh_26));
    data.add(new SkillBean(R.drawable.mh_27));
    data.add(new SkillBean(R.drawable.mh_28));
    data.add(new SkillBean(R.drawable.mh_29));
    data.add(new SkillBean(R.drawable.mh_30));
    data.add(new SkillBean(R.drawable.mh_31));
    data.add(new SkillBean(R.drawable.mh_32));
    data.add(new SkillBean(R.drawable.mh_33));
    data.add(new SkillBean(R.drawable.mh_34));
    data.add(new SkillBean(R.drawable.mh_35));
    data.add(new SkillBean(R.drawable.mh_36));
    data.add(new SkillBean(R.drawable.mh_37));
    data.add(new SkillBean(R.drawable.mh_38));
    data.add(new SkillBean(R.drawable.mh_39));
    data.add(new SkillBean(R.drawable.mh_40));
    data.add(new SkillBean(R.drawable.mh_41));
    data.add(new SkillBean(R.drawable.mh_42));
    data.add(new SkillBean(R.drawable.mh_43));
    data.add(new SkillBean(R.drawable.mh_44));
    data.add(new SkillBean(R.drawable.mh_45));
    data.add(new SkillBean(R.drawable.mh_46));
    data.add(new SkillBean(R.drawable.mh_47));
    data.add(new SkillBean(R.drawable.mh_48));
    data.add(new SkillBean(R.drawable.mh_49));
    data.add(new SkillBean(R.drawable.mh_50));
    data.add(new SkillBean(R.drawable.mh_51));
    data.add(new SkillBean(R.drawable.mh_52));
    data.add(new SkillBean(R.drawable.mh_53));
    data.add(new SkillBean(R.drawable.mh_54));
    data.add(new SkillBean(R.drawable.mh_55));
    data.add(new SkillBean(R.drawable.mh_56));
    data.add(new SkillBean(R.drawable.mh_57));
    data.add(new SkillBean(R.drawable.mh_58));
    data.add(new SkillBean(R.drawable.mh_59));
    data.add(new SkillBean(R.drawable.mh_60));
    data.add(new SkillBean(R.drawable.mh_61));
    data.add(new SkillBean(R.drawable.mh_62));
    data.add(new SkillBean(R.drawable.mh_63));
    data.add(new SkillBean(R.drawable.mh_64));
  }

  public DataRepo(Context context) {
    String[] names = context.getResources().getStringArray(R.array.skill_name);
    for (int i = 0; i < data.size(); i++) {
      data.get(i).setName(names[i]);
    }
  }

  public List<SkillBean> refreshList() {
    List<SkillBean> list = new ArrayList<>();
    list.addAll(data.subList(0, 9));
    return list;
  }

  public List<SkillBean> loadMore(int resId) {
    if (resId <= 0) {
      return refreshList();
    }
    for (int i = 0; i < data.size(); i++) {
      if (data.get(i).getIconRes() == resId) {
        return data.subList(i, i + 9);
      }
    }
    return null;
  }
}
