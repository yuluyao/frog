package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import yuluyao.frog.decor.Divider
import yuluyao.frog.swipe.SwipeCallback
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_divider_vertical.*
import yuluyao.frog.FrogAdapter

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:00
 */
class DividerVerticalFragment : Fragment() {
  val adapter = FrogAdapter<Data>(R.layout.base_item_data_binding)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_vertical, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.layoutManager = LinearLayoutManager(context)
    recycler.addItemDecoration(Divider(2f, R.color.item_decoration))
    setSwipe(recycler)

    recycler.adapter = adapter

    Repo.refresh().subscribe {
      adapter.data = it
      adapter.notifyDataSetChanged()
    }
  }

  private fun setSwipe(recyclerView: RecyclerView) {
    val helper = ItemTouchHelper(SwipeCallback())
    helper.attachToRecyclerView(recyclerView)
  }

//  companion object {
//
//    fun newInstance(): DividerVerticalFragment {
//      val args = Bundle()
//      val fragment = DividerVerticalFragment()
//      fragment.arguments = args
//      return fragment
//    }
//  }

}
