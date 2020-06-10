package yuluyao.frog

import android.support.v7.widget.RecyclerView
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer

open class CleanAdapter<T>(private val layoutId: Int = 0) : RecyclerView.Adapter<CleanAdapter.Holder>() {
  //<editor-fold desc="DSL support">
  private var callback: (CleanAdapter<T>.(Holder, Int) -> Unit)? = null
  operator fun invoke(block: CleanAdapter<T>.(Holder, Int) -> Unit) {
    callback = block
  }
  //</editor-fold>

  var data: ArrayList<T> = arrayListOf()
  override fun getItemCount(): Int = data.size

  // support multiple item types
  private var types: SparseIntArray? = null
  fun initItemViewType(typeArray: IntArray, layoutArray: IntArray) {
    types = SparseIntArray()
    for (i in typeArray.indices) {
      types?.put(typeArray[i], layoutArray[i])
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    val rootView = LayoutInflater.from(parent.context).inflate(types?.get(viewType) ?: layoutId, parent, false)
    return Holder(rootView)
  }


  override fun onBindViewHolder(holder: Holder, position: Int) {
    callback?.invoke(this, holder, position)
  }

  class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer


}

