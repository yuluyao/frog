package com.yuluyao.frog.repo

import android.content.Context
import com.yuluyao.frog.R
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:36
 */
object Repo {


  private val drawables = intArrayOf(R.drawable.mh_14, R.drawable.mh_15, R.drawable.mh_16,
    R.drawable.mh_17, R.drawable.mh_18, R.drawable.mh_19, R.drawable.mh_20, R.drawable.mh_21,
    R.drawable.mh_22, R.drawable.mh_23, R.drawable.mh_24, R.drawable.mh_25, R.drawable.mh_26,
    R.drawable.mh_27, R.drawable.mh_28, R.drawable.mh_29, R.drawable.mh_30, R.drawable.mh_31,
    R.drawable.mh_32, R.drawable.mh_33, R.drawable.mh_34, R.drawable.mh_35, R.drawable.mh_36,
    R.drawable.mh_37, R.drawable.mh_38, R.drawable.mh_39, R.drawable.mh_40, R.drawable.mh_41,
    R.drawable.mh_42, R.drawable.mh_43, R.drawable.mh_44, R.drawable.mh_45, R.drawable.mh_46,
    R.drawable.mh_47, R.drawable.mh_48, R.drawable.mh_49, R.drawable.mh_50, R.drawable.mh_51,
    R.drawable.mh_52, R.drawable.mh_53, R.drawable.mh_54, R.drawable.mh_55, R.drawable.mh_56,
    R.drawable.mh_57, R.drawable.mh_58, R.drawable.mh_59, R.drawable.mh_60, R.drawable.mh_61,
    R.drawable.mh_62, R.drawable.mh_63, R.drawable.mh_64, R.drawable.mh_1, R.drawable.mh_2,
    R.drawable.mh_3, R.drawable.mh_4, R.drawable.mh_5, R.drawable.mh_6, R.drawable.mh_7,
    R.drawable.mh_8, R.drawable.mh_9, R.drawable.mh_10, R.drawable.mh_11, R.drawable.mh_12,
    R.drawable.mh_13)

  private val datas = arrayListOf<Data>()
  fun init(context: Context) {
    val res = context.resources
    val ids = res.getIntArray(R.array.data_id)
    val titles = res.getStringArray(R.array.data_title)
    val contents = res.getStringArray(R.array.data_content)
    val icons = drawables
    for (i in ids.indices) {
      datas.add(Data(ids[i], titles[i], contents[i], icons[i]))
    }
  }

  fun refresh(): Single<ArrayList<Data>> {
    return Single.just(datas.slice(0 until 10) as ArrayList<Data>)
      .delay(300, TimeUnit.MILLISECONDS)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

  fun refresh(count: Int): Single<ArrayList<Data>> {
    return Single.just(datas.slice(0 until count) as ArrayList<Data>)
      .delay(300, TimeUnit.MILLISECONDS)
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
  }

//  fun initData(size: Int): Observable<ArrayList<Data>> {
//    var size = size
//    size = if (size < 0) 0 else size
//    size = if (size > datas.size) datas.size else size
//    return Observable.just(datas.subList(0, size) as ArrayList<Data>)
//      .subscribeOn(Schedulers.io())
//      .delay(300, TimeUnit.MILLISECONDS)
//      .observeOn(AndroidSchedulers.mainThread())
//  }

  fun load(id: Int): Single<ArrayList<Data>> {
    if (id <= 0) {
      return refresh()
    }
    var list: ArrayList<Data> = ArrayList()
    for (i in datas.indices) {
      if (datas[i].id == id) {
        val lastPosition = datas.size - 1//63
        val startPosition = i + 1//61

        val leftCount = lastPosition - startPosition

        if (leftCount <= 0) {//没有了

        } else if (leftCount <= 10) {//没有下一页了
          list = datas.slice(startPosition until lastPosition + 1) as ArrayList<Data>
        } else {//正常，下一页
          list = datas.slice(startPosition until startPosition + 10) as ArrayList<Data>
        }
      }
    }
    return Single.just(list)
      .subscribeOn(Schedulers.io())
      .delay(300, TimeUnit.MILLISECONDS)
      .observeOn(AndroidSchedulers.mainThread())
  }


}

data class Data(
  var id: Int,
  var title: String,
  var content: String,
  var iconRes: Int)
