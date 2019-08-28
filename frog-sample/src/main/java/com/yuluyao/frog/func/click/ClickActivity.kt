package com.yuluyao.frog.func.click

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import kotlinx.android.synthetic.main.activity_click.*
import kotlinx.android.synthetic.main.item_data_common.view.*
import yuluyao.frog.FrogAdapter
import yuluyao.frog.FrogHolder
import yuluyao.frog.click.FrogClickListener
import yuluyao.frog.click.FrogLongClickListener
import yuluyao.frog.click.FrogSingleClickListener

/**
 * Created by wusheng on 2017/9/2.
 */

class ClickActivity : BaseActivity() {

  private var adapter = object : FrogAdapter<Data>(R.layout.item_data_common) {
    override fun convert(holder: FrogHolder, item: Data) {
      holder.itemView.title.text = item.title
    }

  }

  val clickListener = object : FrogClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ClickActivity, "点击 item$position", Toast.LENGTH_SHORT).show()
    }
  }

  val longClickListener = object : FrogLongClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ClickActivity, "长按 item$position", Toast.LENGTH_SHORT).show()
    }
  }

  val singleClickListener = object : FrogSingleClickListener() {
    override fun onItemClicked(position: Int) {
      Toast.makeText(this@ClickActivity, "单击 item$position", Toast.LENGTH_SHORT).show()
    }

  }


  override fun onGetLayoutId(): Int = R.layout.activity_click

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "点击事件"

    /*toolbar.inflateMenu(R.menu.menu_touch)
    toolbar.setOnMenuItemClickListener {
      recyclerView.removeOnItemTouchListener(clickListener)
      recyclerView.removeOnItemTouchListener(longClickListener)
      recyclerView.removeOnItemTouchListener(singleClickListener)
      when (it.itemId) {
        R.id.click -> recyclerView.addOnItemTouchListener(clickListener)
        R.id.longclick -> recyclerView.addOnItemTouchListener(longClickListener)
        R.id.singleclick -> recyclerView.addOnItemTouchListener(singleClickListener)
      }
      true
    }*/

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
    recyclerView.removeOnItemTouchListener(clickListener)
    recyclerView.removeOnItemTouchListener(longClickListener)
    recyclerView.removeOnItemTouchListener(singleClickListener)
    when (item.itemId) {
      R.id.click -> recyclerView.addOnItemTouchListener(clickListener)
      R.id.longclick -> recyclerView.addOnItemTouchListener(longClickListener)
      R.id.singleclick -> recyclerView.addOnItemTouchListener(singleClickListener)
    }
    return true
  }


}