package com.capsule.trunk.decor;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 17:39
 */
public class BaseItemDecoration extends RecyclerView.ItemDecoration {

  protected static final   int LAYOUT_INVALID                   = -1;//未初始化
  protected static final int LAYOUT_VERTICAL                  = 0;//竖向
  protected static final int LAYOUT_HORIZONTAL                = 1;//横向
  protected static final int LAYOUT_GRID                      = 2;//表格
  protected static final int LAYOUT_STAGGERED_GRID_VERTICAL   = 3;//瀑布--vertical
  protected static final int LAYOUT_STAGGERED_GRID_HORIZONTAL = 4;//瀑布--horizontal

  protected int layout_type      = LAYOUT_INVALID;

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    if (layout_type == LAYOUT_INVALID) {
      getLayoutType(parent);
    }
  }

  private void getLayoutType(RecyclerView recyclerView) {
    //init LayoutManager type
    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      if (layoutManager instanceof GridLayoutManager) {
        //GridLayoutManager
        layout_type = LAYOUT_GRID;
      } else {
        if (((LinearLayoutManager) layoutManager).getOrientation()
            == LinearLayoutManager.VERTICAL) {
          //LinearLayoutManager -- vertical
          layout_type = LAYOUT_VERTICAL;
        } else {
          //LinearLayoutManager -- horizontal
          layout_type = LAYOUT_HORIZONTAL;
        }
      }
    } else {
      if (((StaggeredGridLayoutManager) layoutManager).getOrientation()
          == StaggeredGridLayoutManager.VERTICAL) {
        //StaggeredGridLayoutManager -- vertical
        layout_type = LAYOUT_STAGGERED_GRID_VERTICAL;
      } else {
        //StaggeredGridLayoutManager -- horizontal
        layout_type = LAYOUT_STAGGERED_GRID_HORIZONTAL;
      }
    }


  }
}
