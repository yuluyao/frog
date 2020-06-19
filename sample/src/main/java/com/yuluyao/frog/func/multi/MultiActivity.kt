package com.yuluyao.frog.func.multi

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.yuluyao.frog.R
import com.yuluyao.frog.base.BaseActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_fun_multi.*
import kotlinx.android.synthetic.main.item_article_multi_jpg.view.*
import kotlinx.android.synthetic.main.item_article_normal.view.*
import yuluyao.frog.CleanAdapter
import yuluyao.frog.Divider
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.item_article_multi_jpg.view.title as title_multi
import kotlinx.android.synthetic.main.item_article_single_jpg.view.iv as iv_single
import kotlinx.android.synthetic.main.item_article_single_jpg.view.title as title_single

class MultiActivity : BaseActivity() {
  private val adapter = object : CleanAdapter<ArticleBean>(R.layout.item_article_normal,
    R.layout.item_article_single_jpg,
    R.layout.item_article_multi_jpg) {

    override fun getItemViewType(position: Int): Int {
      return data[position].type
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
      val item = data[position]
      when (getItemViewType(position)) {
        ArticleBean.TYPE_NORMAL -> {
          holder.itemView.title.text = item.title
          holder.itemView.content.text = item.content
          holder.itemView.iv.setImageResource(item.imgs[0])
        }
        ArticleBean.TYPE_SINGLE_JPG -> {
          holder.itemView.title_single.text = item.title
          holder.itemView.iv_single.setImageResource(item.imgs[0])
        }
        ArticleBean.TYPE_MULTI_JPG -> {
          holder.itemView.title_multi.text = item.title
          holder.itemView.iv1.setImageResource(item.imgs[0])
          holder.itemView.iv2.setImageResource(item.imgs[1])
          holder.itemView.iv3.setImageResource(item.imgs[2])
        }
      }
    }


  }

  override fun onGetLayoutId(): Int = R.layout.activity_fun_multi
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    title = "Multi Type Item"

    recyclerView.layoutManager = LinearLayoutManager(this)
    recyclerView.addItemDecoration(Divider(8f))
//    recyclerView.addItemDecoration(DividerItemDecoration(this, 1))
    recyclerView.adapter = adapter
    mockArticles().subscribe { it ->
      adapter.data = it
      adapter.notifyDataSetChanged()
    }

  }

  private fun mockArticles(): Single<ArrayList<ArticleBean>> {
    val title = "忙碌的早晨要赶着梳洗换装，处理家里大人小人，总是没有办法吃顿营养的早餐吗?"
    val content = "现在贴心的蒸炉只要按下按钮，不需停下手边的工作，20分钟后就可从容的享用美味健康的早点哦~ 营养分析: 早餐盘提供350大卡热量，鸡蛋为优质蛋白质来原，馒头与地瓜皆可提供碳水化合物，地瓜富含纤维素且低GI值是肠道健康的好朋友~ 冷冻馒头一颗75克约200大卡；蒸煮蛋一颗65克约90大卡，蒸地瓜50克约60大卡。"

    val list = ArrayList<ArticleBean>()

    val e_normal = ArticleBean(ArticleBean.TYPE_NORMAL, title, content,
      intArrayOf(R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3))
    val e_single = ArticleBean(ArticleBean.TYPE_SINGLE_JPG, title, content,
      intArrayOf(R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3))
    val e_multi = ArticleBean(ArticleBean.TYPE_MULTI_JPG, title, content,
      intArrayOf(R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3))

//    list.add(e_normal)
//    list.add(e_normal)
//    list.add(e_normal)
//    list.add(e_normal)
//    list.add(e_normal)
//    list.add(e_normal)
//    list.add(e_normal)
    list.add(e_normal)
    list.add(e_normal)
    list.add(e_multi)
    list.add(e_single)
    list.add(e_normal)
    list.add(e_normal)
    list.add(e_multi)
    list.add(e_single)
    list.add(e_normal)
    list.add(e_multi)
    list.add(e_single)
    list.add(e_normal)


    return Single.just(list)
      .delay(100, TimeUnit.MILLISECONDS)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

}