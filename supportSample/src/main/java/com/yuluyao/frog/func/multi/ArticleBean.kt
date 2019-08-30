package com.yuluyao.frog.func.multi


/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 15:33
 */
data class ArticleBean(
                       var type: Int = 0,
                       var title: String=""  ,
                       var content: String=""  ,
                       var imgs: IntArray= intArrayOf() ) {

  companion object {
    const val TYPE_NORMAL = 1
    const val TYPE_SINGLE_JPG = 2
    const val TYPE_MULTI_JPG = 3
  }
}
