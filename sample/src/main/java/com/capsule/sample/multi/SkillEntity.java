package com.capsule.sample.multi;

import android.text.TextUtils;
import com.capsule.recy.multi.MultiEntity;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 12:59
 */
public class SkillEntity implements MultiEntity {

  public static final int TYPE_ICON        = 1;
  public static final int TYPE_NAME        = 2;
  public static final int TYPE_DESCRIPTION = 3;

  private int icon;
  private String name;
  private String description;

  public SkillEntity(int icon, String name) {
    this.icon = icon;
    this.name = name;
  }

  public SkillEntity(int icon, String name, String description) {
    this.icon = icon;
    this.name = name;
    this.description = description;
  }

  public int getIcon() {
    return icon;
  }

  public void setIcon(int icon) {
    this.icon = icon;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override public int getItemType() {
    int type;
    if (!TextUtils.isEmpty(description)) {
      type = TYPE_DESCRIPTION;
    } else {
      type = TYPE_NAME;
    }
    return type;
  }
}
