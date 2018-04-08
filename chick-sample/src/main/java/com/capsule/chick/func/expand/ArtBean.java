package com.capsule.chick.func.expand;

import capsule.chick.expand.Expandable;
import com.capsule.chick.repo.Data;

/**
 * Created by wusheng on 2017/9/9.
 */

public class ArtBean extends Data implements Expandable {

  public ArtBean(int id, String title, String content, int iconRes) {
    super(id, title, content, iconRes);
  }

  public ArtBean(Data data) {
    super(data.getId(), data.getTitle(), data.getContent(), data.getIconRes());
  }


  @Override public boolean isExpand() {
    return expand;
  }

  private boolean expand;

  public void setExpand(boolean expand){
    this.expand = expand;
  }
}
