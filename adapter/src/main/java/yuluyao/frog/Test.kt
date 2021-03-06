package yuluyao.frog

import android.support.v7.widget.RecyclerView


class R {
  class layout {
    companion object {
      val layout_item1 = 1
      val layout_item2 = 2
      val layout_item3 = 3
    }
  }
}

private var mRecyclerView: RecyclerView? = null
fun test1() {
  // 创建 adapter，需要3种数据：
  // 1，泛型参数 String；
  // 2，布局 R.layout.layout_item1；
  // 3，数据绑定 onBind。
  val adapter = adapter<String>(R.layout.layout_item1) {
    onBind { holder, position ->
    }
  }
  mRecyclerView?.adapter = adapter
}

fun test2() {
  // 显式地声明 adapter 的类型与泛型
  val adapter: CleanAdapter<String>
  adapter = adapter(R.layout.layout_item1) {
    onBind { holder, position ->
    }
  }
  mRecyclerView?.adapter = adapter
}

fun test3() {
  val adapter = adapter<String>(R.layout.layout_item1) {
    // 这里设置的布局，会覆盖 adapter() 函数中的布局参数，即，R.layout.layout_item1无效，最终布局是 R.layout.layout_item2
    // 所以，只在一处设置布局就可以了
    layouts { intArrayOf(R.layout.layout_item2) }

    onBind { holder, position ->
    }
  }
  mRecyclerView?.adapter = adapter
}

fun test4() {
  // 创建 adapter 的另一种写法：
  val adapter = adapter<String>()
  adapter.layouts { intArrayOf(R.layout.layout_item1) }
  adapter.onBind { holder, position -> }
  mRecyclerView?.adapter = adapter
}

fun test5() {
  val adapter = adapter<String>()
  // 多种类型，传入多个 layout
  adapter.layouts { intArrayOf(R.layout.layout_item1, R.layout.layout_item2) }
  // 设置类型，这里的类型数字对应了 layouts 数组的下标。即，从0开始。
  adapter.onItemType { position -> 1 }
  adapter.onBind { holder, position ->  }

}