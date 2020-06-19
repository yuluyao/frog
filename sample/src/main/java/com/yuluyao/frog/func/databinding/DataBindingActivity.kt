package com.yuluyao.frog.func.databinding
//
//import android.os.Bundle
//import android.support.v7.widget.LinearLayoutManager
//import com.yuluyao.frog.R
//import com.yuluyao.frog.base.BaseActivity
//import com.yuluyao.frog.repo.Data
//import com.yuluyao.frog.repo.Repo
//import kotlinx.android.synthetic.main.activity_fun_adapter.*
//import yuluyao.frog.FrogAdapter
//
//class DataBindingActivity : BaseActivity() {
//  private val adapter =  FrogAdapter<Data>(R.layout.item_data_binding)
//
//  override fun onGetLayoutId(): Int = R.layout.activity_fun_adapter
//  override fun onCreate(savedInstanceState: Bundle?) {
//    super.onCreate(savedInstanceState)
//    title = "DataBinding Adapter"
//
//    recyclerView.layoutManager = LinearLayoutManager(this)
//    recyclerView.adapter=adapter
//    Repo.refresh().subscribe {it->
//      adapter.data = it
//      adapter.notifyDataSetChanged()
//    }
//
//  }
//
//}