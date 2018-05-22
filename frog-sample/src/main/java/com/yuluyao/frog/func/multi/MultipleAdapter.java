package com.yuluyao.frog.func.multi;

import android.widget.ImageView;
import android.widget.TextView;
import yuluyao.frog.FrogAdapter;
import yuluyao.frog.FrogHolder;

import com.yuluyao.frog.R;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 15:49
 */
public class MultipleAdapter extends FrogAdapter<ArticleBean, FrogHolder> {

  @Override protected void convert(FrogHolder holder, ArticleBean item) {
    switch (item.getItemType()) {
      case ArticleBean.TYPE_NORMAL:
        ((TextView) holder.itemView.findViewById(R.id.title)).setText(item.getTitle());
        ((TextView) holder.itemView.findViewById(R.id.content)).setText(item.getContent());
        ((ImageView) holder.itemView.findViewById(R.id.iv)).setImageResource(item.getImgs().get(0));
        holder.setClickableId(R.id.iv);
        break;
      case ArticleBean.TYPE_MULTI_JPG:
        ((TextView) holder.itemView.findViewById(R.id.title)).setText(item.getTitle());
        ((ImageView) holder.itemView.findViewById(R.id.iv1)).setImageResource(
            item.getImgs().get(0));
        ((ImageView) holder.itemView.findViewById(R.id.iv2)).setImageResource(
            item.getImgs().get(1));
        ((ImageView) holder.itemView.findViewById(R.id.iv3)).setImageResource(
            item.getImgs().get(2));
        break;
      case ArticleBean.TYPE_SINGLE_JPG:
        ((TextView) holder.itemView.findViewById(R.id.title)).setText(item.getTitle());
        ((ImageView) holder.itemView.findViewById(R.id.iv)).setImageResource(item.getImgs().get(0));
        holder.setClickableId(R.id.iv);
        break;
    }
  }

  @Override protected void onSetItemLayout() {
    setItemLayout(ArticleBean.TYPE_NORMAL, R.layout.item_article_normal);
    setItemLayout(ArticleBean.TYPE_MULTI_JPG, R.layout.item_article_multi_jpg);
    setItemLayout(ArticleBean.TYPE_SINGLE_JPG, R.layout.item_article_single_jpg);
  }
}
