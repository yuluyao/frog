package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.Nullable
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity

/**
 * Created by wusheng on 2017/9/2.
 */

class DividerActivity : BaseActivity() {

  override fun onGetLayoutId(): Int = R.layout.activity_divider
  override fun onCreate(@Nullable savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    val fragment = DividerVerticalFragment()
    supportFragmentManager.beginTransaction()
      .replace(R.id.container, fragment, "verti")
      .commit()
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_divider, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.vertical -> {
        val fragment = DividerVerticalFragment()
        supportFragmentManager.beginTransaction()
          .replace(R.id.container, fragment, "verti")
          .commit()
      }
//      R.id.horizontal -> {
//        val fragment1 = DividerHorizontalFragment.newInstance()
//        supportFragmentManager.beginTransaction()
//          .replace(R.id.container, fragment1, "hori")
//          .commit()
//      }
//      R.id.grid -> {
//        val fragment2 = DividerGridFragment.newInstance()
//        supportFragmentManager.beginTransaction()
//          .replace(R.id.container, fragment2, "grid")
//          .commit()
//      }
//      R.id.staggered_vertical -> {
//        val fragment3 = DividerStaggeredVerticalFragment.newInstance()
//        supportFragmentManager.beginTransaction()
//          .replace(R.id.container, fragment3, "stagger_v")
//          .commit()
//      }
//      R.id.staggered_horizontal -> {
//        val fragment4 = DividerStaggeredHorizontalFragment.newInstance()
//        supportFragmentManager.beginTransaction()
//          .replace(R.id.container, fragment4, "stagger_h")
//          .commit()
//      }
    }
    return true
  }

}
