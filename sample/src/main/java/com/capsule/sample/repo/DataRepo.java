package com.capsule.sample.repo;

import android.content.Context;
import com.capsule.sample.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/10 14:16
 */
public class DataRepo {

  private static List<SkillBean> skills = new ArrayList<>();

  static {
    skills.add(new SkillBean(R.drawable.mh_1));
    skills.add(new SkillBean(R.drawable.mh_2));
    skills.add(new SkillBean(R.drawable.mh_3));
    skills.add(new SkillBean(R.drawable.mh_4));
    skills.add(new SkillBean(R.drawable.mh_5));
    skills.add(new SkillBean(R.drawable.mh_6));
    skills.add(new SkillBean(R.drawable.mh_7));
    skills.add(new SkillBean(R.drawable.mh_8));
    skills.add(new SkillBean(R.drawable.mh_9));
    skills.add(new SkillBean(R.drawable.mh_10));
    skills.add(new SkillBean(R.drawable.mh_11));
    skills.add(new SkillBean(R.drawable.mh_12));
    skills.add(new SkillBean(R.drawable.mh_13));
    skills.add(new SkillBean(R.drawable.mh_14));
    skills.add(new SkillBean(R.drawable.mh_15));
    skills.add(new SkillBean(R.drawable.mh_16));
    skills.add(new SkillBean(R.drawable.mh_17));
    skills.add(new SkillBean(R.drawable.mh_18));
    skills.add(new SkillBean(R.drawable.mh_19));
    skills.add(new SkillBean(R.drawable.mh_20));
    skills.add(new SkillBean(R.drawable.mh_21));
    skills.add(new SkillBean(R.drawable.mh_22));
    skills.add(new SkillBean(R.drawable.mh_23));
    skills.add(new SkillBean(R.drawable.mh_24));
    skills.add(new SkillBean(R.drawable.mh_25));
    skills.add(new SkillBean(R.drawable.mh_26));
    skills.add(new SkillBean(R.drawable.mh_27));
    skills.add(new SkillBean(R.drawable.mh_28));
    skills.add(new SkillBean(R.drawable.mh_29));
    skills.add(new SkillBean(R.drawable.mh_30));
    skills.add(new SkillBean(R.drawable.mh_31));
    skills.add(new SkillBean(R.drawable.mh_32));
    skills.add(new SkillBean(R.drawable.mh_33));
    skills.add(new SkillBean(R.drawable.mh_34));
    skills.add(new SkillBean(R.drawable.mh_35));
    skills.add(new SkillBean(R.drawable.mh_36));
    skills.add(new SkillBean(R.drawable.mh_37));
    skills.add(new SkillBean(R.drawable.mh_38));
    skills.add(new SkillBean(R.drawable.mh_39));
    skills.add(new SkillBean(R.drawable.mh_40));
    skills.add(new SkillBean(R.drawable.mh_41));
    skills.add(new SkillBean(R.drawable.mh_42));
    skills.add(new SkillBean(R.drawable.mh_43));
    skills.add(new SkillBean(R.drawable.mh_44));
    skills.add(new SkillBean(R.drawable.mh_45));
    skills.add(new SkillBean(R.drawable.mh_46));
    skills.add(new SkillBean(R.drawable.mh_47));
    skills.add(new SkillBean(R.drawable.mh_48));
    skills.add(new SkillBean(R.drawable.mh_49));
    skills.add(new SkillBean(R.drawable.mh_50));
    skills.add(new SkillBean(R.drawable.mh_51));
    skills.add(new SkillBean(R.drawable.mh_52));
    skills.add(new SkillBean(R.drawable.mh_53));
    skills.add(new SkillBean(R.drawable.mh_54));
    skills.add(new SkillBean(R.drawable.mh_55));
    skills.add(new SkillBean(R.drawable.mh_56));
    skills.add(new SkillBean(R.drawable.mh_57));
    skills.add(new SkillBean(R.drawable.mh_58));
    skills.add(new SkillBean(R.drawable.mh_59));
    skills.add(new SkillBean(R.drawable.mh_60));
    skills.add(new SkillBean(R.drawable.mh_61));
    skills.add(new SkillBean(R.drawable.mh_62));
    skills.add(new SkillBean(R.drawable.mh_63));
    skills.add(new SkillBean(R.drawable.mh_64));
  }

  public DataRepo(Context context) {
    String[] names = context.getResources().getStringArray(R.array.skill_name);
    int[] ids = context.getResources().getIntArray(R.array.icon_id);
    for (int i = 0; i < skills.size(); i++) {
      skills.get(i).setName(names[i]);
      skills.get(i).setId(ids[i]);
    }
  }

  public List<SkillBean> refreshList() {
    List<SkillBean> list = new ArrayList<>();
    list.addAll(skills.subList(0, 10));
    return list;
  }

  public List<SkillBean> loadMore(int id) {
    if (id <= 0) {
      return refreshList();
    }
    for (int i = 0; i < skills.size(); i++) {
      if (skills.get(i).getId() == id) {
        int lastPosition = skills.size()-1;//63
        int startPosition = i + 1;//61

        int leftCount = lastPosition - startPosition;

        if (leftCount <= 0) {
          return new ArrayList<>();
        } else if (leftCount <= 10) {
          return skills.subList(startPosition, lastPosition + 1);
        } else {
          return skills.subList(startPosition, startPosition + 10);
        }
      }
    }
    return null;
  }






}
