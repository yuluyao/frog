package com.yuluyao.frog

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.stetho.Stetho
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.func.adapter.AdapterActivity
import com.yuluyao.frog.func.databinding.DataBindingActivity
import com.yuluyao.frog.func.divider.DividerActivity
import com.yuluyao.frog.func.touch.ItemTouchActivity
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.activity_main.*
import yuluyao.frog.touch.FrogClickListener
import java.util.*

class MainActivity : BaseActivity() {

  override fun onGetLayoutId(): Int = R.layout.activity_main
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    Repo.init(this )

    title = "主页"
    initView()
    Stetho.initializeWithDefaults(applicationContext)
  }

  private fun initView() {
    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addOnItemTouchListener(object : FrogClickListener() {
      override fun onItemClicked(position: Int) {
        startByPosition(position)

      }
    })
//    recyclerView.addItemDecoration(HeadDecor(R.layout.layout_head, true))

    val adapter = MenuAdapter()
    recyclerView.adapter = adapter

    adapter.data = mockData()
    adapter.notifyDataSetChanged()
  }

  private fun mockData(): ArrayList<MenuItem> {
    val list = ArrayList<MenuItem>()
    list.add(MenuItem("Adapter",0))
    list.add(MenuItem("DataBinding",0))
    list.add(MenuItem("Item Touch",0))
    list.add(MenuItem("FrogDivider",0))
    list.add(MenuItem("EmptyView",0))
    list.add(MenuItem("Expand",0))
    list.add(MenuItem("Footer",0))
    list.add(MenuItem("Header",0))
    list.add(MenuItem("Level",0))
    list.add(MenuItem("LoadMore",0))
    list.add(MenuItem("MultipleItemType",0))
    list.add(MenuItem("...",0))
    list.add(MenuItem("...",0))
    list.add(MenuItem("...",0))
    list.add(MenuItem("...",0))
    list.add(MenuItem("...",0))
    return list
  }

  private fun startByPosition(position: Int) {
    var intent: Intent? = null
    when (position) {
      0 -> intent = Intent(this, AdapterActivity::class.java)
      1 -> intent = Intent(this, DataBindingActivity::class.java)
      2 -> intent = Intent(this, ItemTouchActivity::class.java)
      3 -> intent = Intent(this, DividerActivity::class.java)
//      3 -> intent = Intent(this, EmptyActivity::class.java)
//      4 -> intent = Intent(this, ExpandActivity::class.java)
//      5 -> intent = Intent(this, FootActivity::class.java)
//      6 -> intent = Intent(this, HeadActivity::class.java)
//      7 -> {
//      }
//      8 -> intent = Intent(this, LoadActivity::class.java)
//      9 -> intent = Intent(this, MultipleActivity::class.java)

      else -> return
    }//intent = new Intent(this, LevelActivity.class);
    startActivity(intent)
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
