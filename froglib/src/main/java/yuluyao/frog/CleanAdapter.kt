package yuluyao.frog

import android.support.v7.widget.RecyclerView
import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.extensions.LayoutContainer

abstract class CleanAdapter<T>(private val layoutId: Int = 0) : RecyclerView.Adapter<CleanAdapter.Holder>() {
  var data: ArrayList<T> = arrayListOf()
  override fun getItemCount(): Int = data.size

  private var types: SparseIntArray? = null
  fun initItemViewType(typeArray: IntArray, layoutArray: IntArray) {
    types = SparseIntArray()
    for (i in typeArray.indices) {
      types?.put(typeArray[i], layoutArray[i])
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
    val inflater = LayoutInflater.from(parent.context)
    val layout = types?.get(viewType) ?: layoutId
    val rootView = inflater.inflate(layout, parent, false)
    return Holder(rootView)
  }

  class Holder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer
}

