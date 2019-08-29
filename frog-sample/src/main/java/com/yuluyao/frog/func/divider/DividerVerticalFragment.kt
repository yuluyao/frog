package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import yuluyao.frog.decor.FrogDivider
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.fragment_divider_vertical.*
import yuluyao.frog.FrogAdapter

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:00
 */
class DividerVerticalFragment : Fragment() {
  val adapter = FrogAdapter<Data>(R.layout.base_item_data_binding)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_vertical, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.layoutManager = LinearLayoutManager(context)
    recycler.addItemDecoration(FrogDivider(4f, R.color.item_decoration))

    recycler.adapter = adapter

    Repo.refresh().subscribe {it->
      adapter.data = it
      adapter.notifyDataSetChanged()
    }
  }



}
