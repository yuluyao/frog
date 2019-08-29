package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import yuluyao.frog.decor.FrogDivider
import yuluyao.frog.drag.DragCallback
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.fragment_divider_grid.*
import yuluyao.frog.FrogAdapter

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:52
 */
class DividerGridFragment : Fragment() {
  val adapter = FrogAdapter<Data>(R.layout.item_data_grid)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_grid, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val gridLayoutManager = GridLayoutManager(context, 4)
    gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
      override fun getSpanSize(position: Int): Int {
        return when (position) {
          0,4,8,9 -> 2
          else -> 1
        }
      }
    }

    recycler.layoutManager = gridLayoutManager
    recycler.addItemDecoration(FrogDivider(4f, R.color.item_decoration))
    setDrag(recycler);

    recycler.adapter = adapter
    Repo.refresh(40).subscribe { it->
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

  private fun setDrag(recyclerView: RecyclerView) {
    val helper = ItemTouchHelper(DragCallback())
    helper.attachToRecyclerView(recyclerView)
  }
}
