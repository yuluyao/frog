package com.yuluyao.frog.func.click

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.activity_click.*
import yuluyao.frog.FrogAdapter
import yuluyao.frog.click.FrogClickListener

/**
 * Created by wusheng on 2017/9/2.
 */

class ClickActivity : BaseActivity() {

  private var adapter = object : FrogAdapter<Data>(R.layout.item_data_common) {

  }

  override fun onGetLayoutId(): Int = R.layout.activity_click

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "点击事件"
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addOnItemTouchListener(object : FrogClickListener() {
      override fun onItemClicked(position: Int) {
        Toast.makeText(this@ClickActivity, "点击 item$position", Toast.LENGTH_SHORT).show()
      }
    })

    recyclerView.adapter = adapter
    Repo.refresh().subscribe {
      adapter.data = it
      adapter.notifyDataSetChanged()
    }
  }





}