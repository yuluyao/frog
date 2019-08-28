package com.yuluyao.frog.func.adapter

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.activity_adapter.*
import kotlinx.android.synthetic.main.item_data_common.view.*
import yuluyao.frog.FrogAdapter
import yuluyao.frog.FrogHolder

class AdapterActivity : BaseActivity() {
  private val adapter =object :  FrogAdapter<Data>(R.layout.item_data_common){
    override fun convert(holder: FrogHolder, item: Data) {
      holder.itemView.icon.setImageResource(item.iconRes)
      holder.itemView.title.text = item.title
      holder.itemView.content.text = item.content
    }
  }

  override fun onGetLayoutId(): Int = R.layout.activity_adapter
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Adapter"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter=adapter
    Repo.refresh().subscribe {
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

}