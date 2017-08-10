package com.capsule.capsuleadapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/10 10:32
 */
public class SkillBean implements Parcelable {

  private int iconRes;
  private String name;
  private String description;
  public static final Creator<SkillBean> CREATOR = new Creator<SkillBean>() {
    @Override public SkillBean createFromParcel(Parcel source) {
      return new SkillBean(source);
    }

    @Override public SkillBean[] newArray(int size) {
      return new SkillBean[size];
    }
  };

  @Override public String toString() {
    return "SkillBean{" + "name='" + name + '\'' + '}';
  }

  public SkillBean(int iconRes, String name) {
    this.iconRes = iconRes;
    this.name = name;
  }

  public SkillBean(int iconRes) {
    this.iconRes = iconRes;
  }

  public int getIconRes() {
    return iconRes;
  }

  public void setIconRes(int iconRes) {
    this.iconRes = iconRes;
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

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.iconRes);
    dest.writeString(this.name);
    dest.writeString(this.description);
  }

  protected SkillBean(Parcel in) {
    this.iconRes = in.readInt();
    this.name = in.readString();
    this.description = in.readString();
  }
}
