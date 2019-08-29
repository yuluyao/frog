package com.yuluyao.frog.func.adapter

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.base_activity_data.*
import kotlinx.android.synthetic.main.base_item_data.view.*
import yuluyao.frog.FrogBindingAdapter
import yuluyao.frog.FrogBindingHolder

class AdapterActivity : BaseActivity() {
  private val adapter = object : FrogBindingAdapter<Data>(R.layout.base_item_data) {
    override fun onBindViewHolder(holder: FrogBindingHolder, position: Int) {
      super.onBindViewHolder(holder, position)
      val item = data[position]
      holder.itemView.icon.setImageResource(item.iconRes)
      holder.itemView.title.text = item.title
      holder.itemView.content.text = item.content
    }
  }

  override fun onGetLayoutId(): Int = R.layout.base_activity_data
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Adapter"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    Repo.refresh().subscribe {
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

}