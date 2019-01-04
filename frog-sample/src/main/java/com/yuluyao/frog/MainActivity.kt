package com.yuluyao.frog

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import yuluyao.frog.click.ItemClickListener
import yuluyao.frog.decor.HeadDecor
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.func.anim.AnimActivity
import com.yuluyao.frog.func.click.ClickActivity
import com.yuluyao.frog.func.divider.DividerActivity
import com.yuluyao.frog.func.empty.EmptyActivity
import com.yuluyao.frog.func.expand.ExpandActivity
import com.yuluyao.frog.func.foot.FootActivity
import com.yuluyao.frog.func.head.HeadActivity
import com.yuluyao.frog.func.load.LoadActivity
import com.yuluyao.frog.func.multi.MultipleActivity
import com.facebook.stetho.Stetho

import java.util.ArrayList

class MainActivity : BaseActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "主页"
    initView()
    Stetho.initializeWithDefaults(applicationContext)
  }

  override fun onGetLayoutId(): Int {
    return R.layout.activity_main
  }

  private fun initView() {
    val recyclerView = findViewById<RecyclerView>(R.id.recycler)
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addOnItemTouchListener(object : ItemClickListener() {
      override fun onItemClick(vh: RecyclerView.ViewHolder, position: Int, childView: View) {
        startByPosition(position)
      }
    })
    recyclerView.addItemDecoration(HeadDecor(R.layout.layout_head, true))

    val adapter = MainAdapter()
    recyclerView.adapter = adapter

    adapter.data = mockData()
    adapter.notifyDataSetChanged()
  }

  private fun mockData(): List<String> {
    val list = ArrayList<String>()
    list.add("Animator")
    list.add("ClickListener")
    list.add("Divider")
    list.add("EmptyView")
    list.add("Expand")
    list.add("Footer")
    list.add("Header")
    list.add("Level")
    list.add("LoadMore")
    list.add("MultipleItemType")
    list.add("...")
    list.add("...")
    list.add("...")
    list.add("...")
    list.add("...")
    return list
  }

  private fun startByPosition(position: Int) {
    var intent: Intent? = null
    when (position) {
      0 -> intent = Intent(this, AnimActivity::class.java)
      1 -> intent = Intent(this, ClickActivity::class.java)
      2 -> intent = Intent(this, DividerActivity::class.java)
      3 -> intent = Intent(this, EmptyActivity::class.java)
      4 -> intent = Intent(this, ExpandActivity::class.java)
      5 -> intent = Intent(this, FootActivity::class.java)
      6 -> intent = Intent(this, HeadActivity::class.java)
      7 -> {
      }
      8 -> intent = Intent(this, LoadActivity::class.java)
      9 -> intent = Intent(this, MultipleActivity::class.java)

      else -> return
    }//intent = new Intent(this, LevelActivity.class);
    startActivity(intent)
  }
}
