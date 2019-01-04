package yuluyao.frog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class BaseAdapter<T>(val layoutId: Int) : RecyclerView.Adapter<BaseViewHolder>() {
  var data: ArrayList<T> = arrayListOf()
  override fun getItemCount(): Int = data.size
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    val itemView = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    return BaseViewHolder(itemView)
  }


  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {

  }

}

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

}