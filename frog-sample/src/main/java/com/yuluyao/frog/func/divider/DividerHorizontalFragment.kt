package com.yuluyao.frog.func.divider

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import yuluyao.frog.decor.Divider
import com.yuluyao.frog.R
import com.yuluyao.frog.repo.Data
import com.yuluyao.frog.repo.Repo
import io.reactivex.annotations.NonNull
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_divider_horizontal.*
import yuluyao.frog.FrogAdapter

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:33
 */
class DividerHorizontalFragment : Fragment() {
  val adapter = FrogAdapter<Data>(R.layout.item_data_horizontal)

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                            savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_divider_horizontal, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    recycler.addItemDecoration(Divider(2f, R.color.item_decoration))

    recycler.adapter = adapter

    Repo.refresh().subscribe {
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

//  companion object {
//
//    fun newInstance(): DividerHorizontalFragment {
//      val args = Bundle()
//      val fragment = DividerHorizontalFragment()
//      fragment.arguments = args
//      return fragment
//    }
//  }

}
