package com.yuluyao.frog.func.adapter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.base_activity_data.*
import kotlinx.android.synthetic.main.base_item_data.view.*
import yuluyao.frog.CleanAdapter

class AdapterActivity : BaseActivity() {
  private val adapter = object : CleanAdapter<Data>(R.layout.base_item_data) {
    override fun onBindViewHolder(holder: Holder, position: Int) {
      val item = data[position]
      holder.itemView.icon.setImageResource(item.iconRes)
      holder.itemView.title.text = item.title
      holder.itemView.content.text = item.content
    }

    override fun getItemViewType(position: Int): Int {
      return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
      return super.onCreateViewHolder(parent, viewType)

    }
  }

  override fun onGetLayoutId(): Int = R.layout.base_activity_data
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Adapter"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    Repo.refresh().subscribe {it->
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

}