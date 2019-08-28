package yuluyao.frog.decor;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 17:39
 */
public class BaseItemDecoration extends RecyclerView.ItemDecoration {

  protected static final int LAYOUT_INVALID = -1;//未初始化
  protected static final int LAYOUT_VERTICAL = 0;//竖向
  protected static final int LAYOUT_HORIZONTAL = 1;//横向
  protected static final int LAYOUT_GRID = 2;//表格
  protected static final int LAYOUT_STAGGERED_GRID_VERTICAL = 3;//瀑布--vertical
  protected static final int LAYOUT_STAGGERED_GRID_HORIZONTAL = 4;//瀑布--horizontal

  protected int layout_type = LAYOUT_INVALID;
  protected RecyclerView recyclerView;

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    if (layout_type == LAYOUT_INVALID) {
      getLayoutType(parent);
    }
  }

  private void getLayoutType(RecyclerView recyclerView) {
    this.recyclerView = recyclerView;
    //init LayoutManager type
    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      if (layoutManager instanceof GridLayoutManager) {
        //GridLayoutManager
        layout_type = LAYOUT_GRID;
      } else {
        if (((LinearLayoutManager) layoutManager).getOrientation() ==
            LinearLayoutManager.VERTICAL) {
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
