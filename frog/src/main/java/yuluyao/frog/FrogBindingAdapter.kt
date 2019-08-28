package yuluyao.frog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class FrogBindingAdapter<T>(private val layoutId: Int) : RecyclerView.Adapter<FrogBindingHolder>() {
  var data: ArrayList<T> = arrayListOf()

  override fun getItemCount(): Int = data.size
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogBindingHolder {
    val inflater = LayoutInflater.from(parent.context)
    try {
      val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
      return FrogBindingHolder(binding.root)
    } catch (e: Exception) {
    } finally {
      val root = inflater.inflate(layoutId, parent, false)
      return FrogBindingHolder(root)
    }
  }

  override fun onBindViewHolder(holder: FrogBindingHolder, position: Int) {
    val item = data[position]
    holder.bind(item)
  }
}

class FrogBindingHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val binding: ViewDataBinding? = DataBindingUtil.getBinding(itemView)
  fun bind(any: Any?) {
    binding?.setVariable(BR.item, any)
    binding?.executePendingBindings()
  }
}