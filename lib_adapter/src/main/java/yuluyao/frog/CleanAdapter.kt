package yuluyao.frog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer

open class CleanAdapter<T>(private vararg val layouts: Int) : RecyclerView.Adapter<CleanAdapter.Holder>() {
  //<editor-fold desc="DSL support">
  private var callback: (CleanAdapter<T>.(Holder, Int) -> Unit)? = null
  operator fun invoke(block: CleanAdapter<T>.(Holder, Int) -> Unit) {
    callback = block
  }
  //</editor-fold>

  var data: ArrayList<T> = arrayListOf()
  override fun getItemCount(): Int = data.size
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(LayoutInflater.from(parent.context).inflate(layouts[viewType], parent, false))
  override fun onBindViewHolder(holder: Holder, position: Int) = callback?.invoke(this, holder, position) ?: Unit
  class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer

}

