package com.yuluyao.frog.func.adapter

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Character
import com.yuluyao.frog.repo.DataStore
import kotlinx.android.synthetic.main.activity_fun_adapter.*
import kotlinx.android.synthetic.main.item_data_adapter.view.*
import yuluyao.frog.CleanAdapter

class FFAdapterActivity : BaseActivity() {
  private val adapter = object : CleanAdapter<Character>(R.layout.item_data_adapter) {

    override fun onBindViewHolder(holder: Holder, position: Int) {
      val item = data[position]
      val itemView = holder.itemView

      itemView.icon.setImageResource(item.iconRes)
      itemView.title.text  = item.title
      itemView.content.text = item.content
    }

  }


  override fun onGetLayoutId(): Int = R.layout.activity_fun_adapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Adapter"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    DataStore.refresh().subscribe {
      adapter.data.clear()
      adapter.data.addAll(it)
      adapter.notifyDataSetChanged()
    }

  }

}