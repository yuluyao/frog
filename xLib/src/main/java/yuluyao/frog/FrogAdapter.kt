package yuluyao.frog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class FrogAdapter<T>(private val layoutId: Int) :
    RecyclerView.Adapter<FrogHolder>() {
  var data: ArrayList<T> = arrayListOf()

  override fun getItemCount(): Int = data.size
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
    return if (binding != null) {
      FrogHolder(binding.root)
    } else {
      FrogHolder(inflater.inflate(layoutId, parent, false))
    }

  }

  override fun onBindViewHolder(holder: FrogHolder, position: Int) {
    val item = data[position]
    holder.bind(item)
  }
}

class FrogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  val binding: ViewDataBinding? = DataBindingUtil.getBinding(itemView)
  fun bind(any: Any?) {
    binding?.setVariable(BR.item, any)
    binding?.executePendingBindings()
  }
}

@BindingAdapter("src")
fun imageResource(v: ImageView, res: Int) {
  v.setImageResource(res)
}

