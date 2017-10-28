package com.capsule.sample.func.level;

import com.capsule.recy.level.TreeEntity;
import java.util.List;

/**
 * Created by wusheng on 2017/9/15.
 */

public class Bean implements TreeEntity<Bean> {

  private Bean       parent;
  private List<Bean> children;
  private boolean flag_expand = false;

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override public Bean getParent() {
    return parent;
  }

  @Override public void setParent(Bean treeEntity) {
    parent = treeEntity;
  }

  @Override public List<Bean> getChildren() {
    return children;
  }

  @Override public void setChildren(List<Bean> list) {
    children = list;
  }

  @Override public boolean hasParent() {
    return parent != null;
  }

  @Override public boolean hasChildren() {
    return children != null && children.size() > 0;
  }

  @Override public boolean isExpanded() {
    return flag_expand;
  }
}
