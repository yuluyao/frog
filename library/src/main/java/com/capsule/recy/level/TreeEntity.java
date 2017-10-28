package com.capsule.recy.level;

import java.util.List;

/**
 * Created by wusheng on 2017/9/15.
 */

public interface TreeEntity<T extends TreeEntity> {

  T getParent();

  void setParent(T treeEntity);

  List<T> getChildren();

  void setChildren(List<T> list);

  boolean hasParent();

  boolean hasChildren();

  boolean isExpanded();

}
