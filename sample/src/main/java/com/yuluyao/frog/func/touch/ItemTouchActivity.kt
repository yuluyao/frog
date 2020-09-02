package com.yuluyao.frog.func.touch

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Character
import com.yuluyao.frog.repo.DataStore
import kotlinx.android.synthetic.main.activity_fun_touch.*
import kotlinx.android.synthetic.main.item_data_touch.view.*
import yuluyao.frog.CleanAdapter
import yuluyao.frog.touch.*

/**
 * Created by wusheng on 2017/9/2.
 */

class ItemTouchActivity : BaseActivity() {

  private var adapter = object : CleanAdapter<Character>(R.layout.item_data_touch) {
    override fun onBindViewHolder(holder: Holder, position: Int) {
      val item = data[position]
      holder.itemView.title.text = item.title
      holder.itemView.content.text = item.content
      holder.itemView.icon.setImageResource(item.iconRes)
    }
  }

  override fun onGetLayoutId(): Int = R.layout.activity_fun_touch
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Item Touch"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    DataStore.refresh().subscribe {
      adapter.data.clear()
      adapter.data.addAll(it)
      adapter.notifyDataSetChanged()
    }
  }


  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_touch, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    item ?: return true
    recyclerView.removeOnItemTouchListener(click_listener)
    recyclerView.removeOnItemTouchListener(long_click_listenr)
    recyclerView.removeOnItemTouchListener(single_click_listener)
    recyclerView.removeOnItemTouchListener(intercept_click_listener)
    recyclerView.removeOnItemTouchListener(child_click_listener)
    recyclerView.removeOnItemTouchListener(child_single_click_listener)
    when (item.itemId) {
      R.id.click -> recyclerView.addOnItemTouchListener(click_listener)
      R.id.long_click -> recyclerView.addOnItemTouchListener(long_click_listenr)
      R.id.single_click -> recyclerView.addOnItemTouchListener(single_click_listener)
      R.id.intercept_click -> recyclerView.addOnItemTouchListener(intercept_click_listener)
      R.id.child_click -> recyclerView.addOnItemTouchListener(child_click_listener)
      R.id.child_single_click -> recyclerView.addOnItemTouchListener(child_single_click_listener)
    }
    return true
  }


  private val click_listener = object : OnItemClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ItemTouchActivity, "点击 item$position", Toast.LENGTH_SHORT).show()
      Log.i("vegeta", "点击 item$position")
    }
  }

  private val long_click_listenr = object : OnItemLongClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ItemTouchActivity, "长按 item$position", Toast.LENGTH_SHORT).show()
      Log.i("vegeta", "长按 item$position")
    }
  }

  private val single_click_listener = object : OnItemSingleClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ItemTouchActivity, "单击 item$position", Toast.LENGTH_SHORT).show()
      Log.i("vegeta", "单击 item$position")
    }
  }

  private val intercept_click_listener = object : OnItemInterceptClickListener() {
    override fun onItemClicked(position: Int): Boolean {
      if (position < 3) {
        Toast.makeText(this@ItemTouchActivity, "不响应点击 $position", Toast.LENGTH_SHORT).show()
        Log.i("vegeta", "不响应点击 $position")
        return false
      } else {
        Toast.makeText(this@ItemTouchActivity, "响应点击 $position", Toast.LENGTH_SHORT).show()
        Log.i("vegeta", "响应点击 $position")
        return true
      }
    }
  }

  private val child_click_listener = object : OnItemChildClickListener() {
    override val listenedChildrenIds: IntArray = intArrayOf(R.id.icon, R.id.title, R.id.root)
    override fun onChildClicked(position: Int, viewId: Int) {
      when (viewId) {
        R.id.icon -> {
          Toast.makeText(this@ItemTouchActivity, "click icon", Toast.LENGTH_SHORT).show()
          Log.i("vegeta", "click icon")
        }
        R.id.title -> {
          Toast.makeText(this@ItemTouchActivity, "click title", Toast.LENGTH_SHORT).show()
          Log.i("vegeta", "click title")
        }
        R.id.root -> {
          Toast.makeText(this@ItemTouchActivity, "click root view", Toast.LENGTH_SHORT).show()
          Log.i("vegeta", "click root view")
        }
      }
    }
  }

  private val child_single_click_listener = object : OnItemChildSingleClickListener() {
    override val listenedChildrenIds: IntArray = intArrayOf(R.id.icon, R.id.title, R.id.root)
    override fun onChildClicked(position: Int, viewId: Int) {
      when (viewId) {
        R.id.icon -> {
          Toast.makeText(this@ItemTouchActivity, "single click icon", Toast.LENGTH_SHORT).show()
          Log.i("vegeta", "single click icon")
        }
        R.id.title -> {
          Toast.makeText(this@ItemTouchActivity, "single click title", Toast.LENGTH_SHORT).show()
          Log.i("vegeta", "single click title")
        }
        R.id.root -> {
          Toast.makeText(this@ItemTouchActivity, "single click root view", Toast.LENGTH_SHORT).show()
          Log.i("vegeta", "single click root view")
        }
      }
    }
  }

}