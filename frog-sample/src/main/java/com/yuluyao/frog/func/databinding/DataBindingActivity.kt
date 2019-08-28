package com.yuluyao.frog.func.databinding

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.base_activity_data.*
import kotlinx.android.synthetic.main.base_item_data.view.*
import yuluyao.frog.FrogAdapter
import yuluyao.frog.FrogBindingAdapter
import yuluyao.frog.FrogHolder

class DataBindingActivity : BaseActivity() {
  private val adapter =  FrogBindingAdapter<Data>(R.layout.item_data_binding)

  override fun onGetLayoutId(): Int = R.layout.base_activity_data
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "DataBinding Adapter"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter=adapter
    Repo.refresh().subscribe {
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

}