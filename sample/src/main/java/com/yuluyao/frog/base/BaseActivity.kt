package com.yuluyao.frog.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.yuluyao.frog.R

/**
 * Created by wusheng on 2017/9/2.
 */

abstract class BaseActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(onGetLayoutId())

    initToolbar()
  }

  private fun initToolbar() {
    val toolbar : Toolbar? = findViewById<View>(R.id.toolbar) as Toolbar? ?: return
    setSupportActionBar(toolbar)
    supportActionBar?.setDisplayShowTitleEnabled(true)
    toolbar?.setNavigationOnClickListener { onBackPressed() }

  }

  protected abstract fun onGetLayoutId(): Int
}
