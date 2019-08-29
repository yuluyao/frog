package yuluyao.frog.drag

import android.content.res.Resources
import android.os.Build

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import yuluyao.frog.FrogAdapter

import java.util.Collections

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/14 17:37
 */
class DragCallback : ItemTouchHelper.Callback() {

  override fun getMovementFlags(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder): Int {
    val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    val swipeFlags = 0
    return makeMovementFlags(dragFlags, swipeFlags)
  }

  override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                      target: RecyclerView.ViewHolder): Boolean {
    //得到拖动ViewHolder的position
    val fromPosition = viewHolder.adapterPosition
    //得到目标ViewHolder的position
    val toPosition = target.adapterPosition
    val list = (recyclerView.adapter as FrogAdapter<*>).data
    if (fromPosition < toPosition) {
      for (i in fromPosition until toPosition) {
        Collections.swap(list, i, i + 1)
      }
    } else {
      for (i in fromPosition downTo toPosition + 1) {
        Collections.swap(list, i, i - 1)
      }
    }
    recyclerView.adapter!!.notifyItemMoved(fromPosition, toPosition)
    return true
  }

  override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

  }

  override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
    super.onSelectedChanged(viewHolder, actionState)
    when (actionState) {
      ItemTouchHelper.ACTION_STATE_IDLE -> {
      }
      ItemTouchHelper.ACTION_STATE_SWIPE -> {
      }
      ItemTouchHelper.ACTION_STATE_DRAG -> if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        viewHolder!!.itemView.elevation = 4 * Resources.getSystem().displayMetrics.density
      }
    }
  }

  override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
    super.clearView(recyclerView, viewHolder)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      viewHolder.itemView.elevation = 0f
    }
  }
}
