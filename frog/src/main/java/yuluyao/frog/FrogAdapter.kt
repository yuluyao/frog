package yuluyao.frog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class FrogAdapter<T>(private val layoutId: Int) : RecyclerView.Adapter<FrogHolder>() {
  var data: ArrayList<T> = arrayListOf()

  override fun getItemCount(): Int = data.size
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrogHolder {
    val inflater = LayoutInflater.from(parent.context)
    val root = inflater.inflate(layoutId, parent, false)
    return FrogHolder(root)
  }

  override fun onBindViewHolder(holder: FrogHolder, position: Int) {
    val item = data[position]
    convert(holder, item)
  }

  abstract fun convert(holder: FrogHolder, item: T)


}

class FrogHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//  val binding: ViewDataBinding? = DataBindingUtil.getBinding(itemView)
//  fun bind(any: Any?) {
//    binding?.setVariable(BR.item, any)
//    binding?.executePendingBindings()
//  }
}