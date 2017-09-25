package com.capsule.sample.func.multi;

import com.capsule.trunk.multi.MultiEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 15:33
 */
public class ArticleBean implements MultiEntity {

  public static final int TYPE_NORMAL = 1;
  public static final int TYPE_SINGLE_JPG = 2;
  public static final int TYPE_MULTI_JPG = 3;

  private int id;
  private int type;
  private String title;
  private String content;
  private List<Integer> imgs;

  public ArticleBean(int type, String title, String content, List<Integer> imgs) {
    this.type = type;
    this.title = title;
    this.content = content;
    this.imgs = imgs;
  }

  public ArticleBean(int type, String title, String content, int[] imgs) {
    this.type = type;
    this.title = title;
    this.content = content;
    this.imgs = new ArrayList<>();
    for (int i : imgs) {
      this.imgs.add(i);
    }
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
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

  public List<Integer> getImgs() {
    return imgs;
  }

  public void setImgs(List<Integer> imgs) {
    this.imgs = imgs;
  }

  @Override public int getItemType() {
    return type;
  }
}
