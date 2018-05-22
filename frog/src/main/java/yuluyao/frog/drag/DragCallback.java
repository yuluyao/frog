package yuluyao.frog.drag;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import yuluyao.frog.FrogAdapter;

import java.util.Collections;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 17:37
 */
public class DragCallback extends ItemTouchHelper.Callback {

  @Override
  public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    int dragFlags;
    int swipeFlags;
    dragFlags =
        ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    swipeFlags = 0;
    return makeMovementFlags(dragFlags, swipeFlags);
  }

  @Override public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
      RecyclerView.ViewHolder target) {
    int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
    int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
    List list = ((FrogAdapter) recyclerView.getAdapter()).getData();
    if (fromPosition < toPosition) {
      for (int i = fromPosition; i < toPosition; i++) {
        if (list != null) {
          Collections.swap(list, i, i + 1);
        }
      }
    } else {
      for (int i = fromPosition; i > toPosition; i--) {
        if (list != null) {
          Collections.swap(list, i, i - 1);
        }
      }
    }
    recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
    return true;
  }

  @Override public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

  }

  @Override public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
    super.onSelectedChanged(viewHolder, actionState);
    switch (actionState) {
      case ItemTouchHelper.ACTION_STATE_IDLE:
        break;
      case ItemTouchHelper.ACTION_STATE_SWIPE:
        break;
      case ItemTouchHelper.ACTION_STATE_DRAG:
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
          viewHolder.itemView.setElevation(6f);
        }
        break;
    }
  }

  @Override public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
    super.clearView(recyclerView, viewHolder);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      viewHolder.itemView.setElevation(0f);
    }
  }
}
