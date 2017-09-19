package com.capsule.sample.repo;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/19 20:50
 */
public class Head {

  private int id;
  private int iconRes;
  private String name;
  private String description;

  public Head(int id, int iconRes, String name, String description) {
    this.id = id;
    this.iconRes = iconRes;
    this.name = name;
    this.description = description;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
}
