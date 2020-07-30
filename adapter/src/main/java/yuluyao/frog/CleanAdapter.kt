package yuluyao.frog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer

open class CleanAdapter<T>(private vararg var layouts: Int) : RecyclerView.Adapter<CleanAdapter.Holder>() {

  val data: ArrayList<T> = arrayListOf()
  override fun getItemCount(): Int = data.size
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(LayoutInflater.from(parent.context).inflate(layouts[viewType], parent, false))
  override fun onBindViewHolder(holder: Holder, position: Int) = onBindBlock?.invoke(this, holder, position) ?: Unit

  fun layouts(block: () -> IntArray) {
    layouts = block.invoke()
  }

  override fun getItemViewType(position: Int): Int {
    return typeCallback?.invoke(this, position) ?: super.getItemViewType(position)
  }

  private var typeCallback: (CleanAdapter<T>.(position: Int) -> Int)? = null
  fun onItemType(block: (CleanAdapter<T>.(position: Int) -> Int)?) {
    typeCallback = block
  }

  private var onBindBlock: (CleanAdapter<T>.(Holder, Int) -> Unit)? = null
  fun onBind(block: (CleanAdapter<T>.(holder: Holder, position: Int) -> Unit)?) {
    onBindBlock = block
  }

  class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}

fun <T> adapter(vararg layouts: Int, initBlock: (CleanAdapter<T>.() -> Unit)? = null): CleanAdapter<T> {
  val adapter = CleanAdapter<T>()
  adapter.layouts { layouts }
  initBlock?.let { adapter.it() }
  return adapter
}


