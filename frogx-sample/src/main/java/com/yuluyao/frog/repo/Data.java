package com.yuluyao.frog.repo;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:33
 */
public class Data {

  private int id;
  private String title;
  private String content;
  private int iconRes;

  public Data(int id, String title, String content, int iconRes) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.iconRes = iconRes;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getIconRes() {
    return iconRes;
  }

  public void setIconRes(int iconRes) {
    this.iconRes = iconRes;
  }
}
