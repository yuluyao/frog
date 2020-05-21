package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.fragment_divider_staggered_horizontal.*
import kotlinx.android.synthetic.main.item_data_stagger_horizontal.view.*
import yuluyao.frog.CleanAdapter
import yuluyao.frog.Divider
import yuluyao.frog.drag.DragCallback

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 20:06
 */
class DividerStaggeredHorizontalFragment : Fragment() {
  val adapter = object : CleanAdapter<Data>(R.layout.item_data_stagger_horizontal){
    override fun onBindViewHolder(holder: Holder, position: Int) {
      holder.itemView.title.text= data[position].title

    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_staggered_horizontal, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.layoutManager = StaggeredGridLayoutManager(4,
      StaggeredGridLayoutManager.HORIZONTAL)
    recycler.addItemDecoration(Divider(8f, context!!.resources.getColor(R.color.item_decoration),false))
    setDrag(recycler)

    recycler.adapter = adapter
    Repo.refresh(50).subscribe { it ->
        adapter.data = it
        adapter.notifyDataSetChanged()
      }
  }

  private fun setDrag(recyclerView: RecyclerView) {
    val helper = ItemTouchHelper(DragCallback())
    helper.attachToRecyclerView(recyclerView)
  }


}
