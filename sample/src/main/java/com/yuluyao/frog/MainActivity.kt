package com.yuluyao.frog

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.stetho.Stetho
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.func.adapter.AdapterActivity
import com.yuluyao.frog.func.divider.DividerActivity
import com.yuluyao.frog.func.adapter.FFAdapterActivity
import com.yuluyao.frog.func.multi.MultiActivity
import com.yuluyao.frog.func.touch.ItemTouchActivity
import kotlinx.android.synthetic.main.activity_main.*
import yuluyao.frog.touch.OnItemClickListener
import java.util.*

class MainActivity : BaseActivity() {

  override fun onGetLayoutId(): Int = R.layout.activity_main
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    title = "主页"
    initView()
    Stetho.initializeWithDefaults(applicationContext)
  }

  private fun initView() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addOnItemTouchListener(object : OnItemClickListener() {
      override fun onItemClicked(position: Int) {
        startByPosition(position)
      }
    })

    recyclerView.adapter = adapter

    adapter.data = mockData()
    adapter.notifyDataSetChanged()
  }

  val adapter = MenuAdapter()

  private fun mockData(): ArrayList<MenuItem> {
    val list = ArrayList<MenuItem>()
    list.add(MenuItem("adapter", 0))
    list.add(MenuItem("multi-item support", 0))
    list.add(MenuItem("item touch", 0))
    list.add(MenuItem("divider", 0))
    list.add(MenuItem("adapter2", 0))

    return list
  }

  private fun startByPosition(position: Int) {
    val intent: Intent? = when (adapter.data[position].title) {
      "adapter" -> Intent(this, AdapterActivity::class.java)
      "multi-item support" -> Intent(this, MultiActivity::class.java)
      "item touch" -> Intent(this, ItemTouchActivity::class.java)
      "divider" -> Intent(this, DividerActivity::class.java)
      "adapter2" -> Intent(this, FFAdapterActivity::class.java)
      else -> null
    }
    intent?.let {
      startActivity(it)
    }
  }

  class MenuItem(val title: String, val id: Int)

  class MenuAdapter : RecyclerView.Adapter<MenuAdapter.Holder>() {
    var data: ArrayList<MenuItem> = arrayListOf()
    override fun getItemCount(): Int = data.size
    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): Holder {
      val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
      return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
      holder.tvTitle.text = data[position].title
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
    }
  }
}
