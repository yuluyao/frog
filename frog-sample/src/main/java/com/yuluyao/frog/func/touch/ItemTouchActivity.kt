package com.yuluyao.frog.func.touch

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.activity_touch.*
import kotlinx.android.synthetic.main.item_touch.view.*
import yuluyao.frog.FrogAdapter
import yuluyao.frog.FrogHolder
import yuluyao.frog.click.*

/**
 * Created by wusheng on 2017/9/2.
 */

class ItemTouchActivity : BaseActivity() {

  private var adapter = object : FrogAdapter<Data>(R.layout.item_touch) {
    override fun onBindViewHolder(holder: FrogHolder, position: Int) {
      super.onBindViewHolder(holder, position)
      val item = data[position]
      holder.itemView.title.text = item.title
      holder.itemView.content.text = item.content
      holder.itemView.icon.setImageResource(item.iconRes)
    }
  }

  override fun onGetLayoutId(): Int = R.layout.activity_touch
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Item Touch"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.adapter = adapter
    Repo.refresh().subscribe {
      adapter.data = it
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
    when (item.itemId) {
      R.id.click -> recyclerView.addOnItemTouchListener(click_listener)
      R.id.long_click -> recyclerView.addOnItemTouchListener(long_click_listenr)
      R.id.single_click -> recyclerView.addOnItemTouchListener(single_click_listener)
      R.id.intercept_click -> recyclerView.addOnItemTouchListener(intercept_click_listener)
      R.id.child_click -> recyclerView.addOnItemTouchListener(child_click_listener)
    }
    return true
  }


  private val click_listener = object : FrogClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ItemTouchActivity, "点击 item$position", Toast.LENGTH_SHORT).show()
    }
  }

  private val long_click_listenr = object : FrogLongClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ItemTouchActivity, "长按 item$position", Toast.LENGTH_SHORT).show()
    }
  }

  private val single_click_listener = object : FrogSingleClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ItemTouchActivity, "单击 item$position", Toast.LENGTH_SHORT).show()
    }
  }

  private val intercept_click_listener = object : FrogInterceptClickListener() {
    override fun onItemClicked(position: Int): Boolean {
      if (position < 3) {
        Toast.makeText(this@ItemTouchActivity, "不响应点击 $position", Toast.LENGTH_SHORT).show()
        return false
      } else {
        Toast.makeText(this@ItemTouchActivity, "响应点击 $position", Toast.LENGTH_SHORT).show()
        return true
      }
    }
  }

  private val child_click_listener = object : FrogChildClickListener() {
    override val listenedChildrenIds: IntArray = intArrayOf(R.id.icon, R.id.title, R.id.root)
    override fun onChildClicked(position: Int, viewId: Int) {
      when (viewId) {
        R.id.icon ->
          Toast.makeText(this@ItemTouchActivity, "click icon", Toast.LENGTH_SHORT).show()
        R.id.title ->
          Toast.makeText(this@ItemTouchActivity, "click title", Toast.LENGTH_SHORT).show()
        R.id.root ->
          Toast.makeText(this@ItemTouchActivity, "click root view", Toast.LENGTH_SHORT).show()
      }
    }
  }

}