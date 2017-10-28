package capsule.trunk.swipe;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 18:46
 */
public class SwipeCallback extends ItemTouchHelper.Callback {

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    int dragFlags;
    int swipeFlags;
    if (isLinearVerticalLayout(recyclerView)) {
      dragFlags = 0;
      swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    } else {
      throw new RuntimeException(
          "Swipe gesture must be act on linear vertical RecyclerView!Set LinearLayoutManager to your RecyclerView!");
    }
    return makeMovementFlags(dragFlags, swipeFlags);
  }

  @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      RecyclerView.ViewHolder target) {
    return false;
  }

  @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    //List list = ((Adapter) recyclerView.getAdapter()).getData();

  }

  @Override
  public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      float dX, float dY, int actionState, boolean isCurrentlyActive) {
    //if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
    //  if (Math.abs(dX) <= recyclerView.getWidth()) {
    //    viewHolder.itemView.scrollTo(-(int) dX, 0);
    //  }
    //}
    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

  }

  private boolean isLinearVerticalLayout(RecyclerView recyclerView) {
    //init LayoutManager type
    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
    if (layoutManager instanceof LinearLayoutManager) {
      if (layoutManager instanceof GridLayoutManager) {
        //GridLayoutManager
        //layout_type = LAYOUT_GRID;
        return false;
      } else {
        if (((LinearLayoutManager) layoutManager).getOrientation()
            == LinearLayoutManager.VERTICAL) {
          //LinearLayoutManager -- vertical
          //layout_type = LAYOUT_VERTICAL;
          return true;
        } else {
          //LinearLayoutManager -- horizontal
          //layout_type = LAYOUT_HORIZONTAL;
          return false;
        }
      }
    } else {
      if (((StaggeredGridLayoutManager) layoutManager).getOrientation()
          == StaggeredGridLayoutManager.VERTICAL) {
        //StaggeredGridLayoutManager -- vertical
        //layout_type = LAYOUT_STAGGERED_GRID_VERTICAL;
        return false;
      } else {
        //StaggeredGridLayoutManager -- horizontal
        //layout_type = LAYOUT_STAGGERED_GRID_HORIZONTAL;
        return false;
      }
    }
  }
}
