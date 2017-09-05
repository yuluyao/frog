package com.capsule.sample.func.multi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.capsule.sample.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 12:03
 */
public class MultipleActivity extends AppCompatActivity {

  private RecyclerView    recyclerView;
  private MultipleAdapter adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multi);
    setTitle("多重类型item");
    adapter = new MultipleAdapter();

    recyclerView = (RecyclerView) findViewById(R.id.rv);
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    //recyclerView.addItemDecoration(new SimpleDecoration(this));
    //recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView) {
    //  @Override
    //  public void onItemClick(RecyclerView.ViewHolder vh, int position, View childView) {
    //    Toast.makeText(MultipleActivity.this, "点击图片", Toast.LENGTH_SHORT).show();
    //  }
    //
    //  @Override public void onItemClick(RecyclerView.ViewHolder vh, int position) {
    //    Toast.makeText(MultipleActivity.this, "点击item", Toast.LENGTH_SHORT).show();
    //  }
    //});
    //recyclerView.addOnItemTouchListener(new SimpleClickListener(recyclerView) {
    //  @Override public void onItemClick(RecyclerView.ViewHolder vh, int position) {
    //    Toast.makeText(MultipleActivity.this, "点击item", Toast.LENGTH_SHORT).show();
    //  }
    //});

    recyclerView.setAdapter(adapter);
    adapter.setData(mockArticles());
    adapter.notifyDataSetChanged();
  }

  //private List<SkillEntity> mockData() {
  //  List<SkillEntity> list = new ArrayList<>();
  //  list.add(new SkillEntity(R.drawable.mh_1, "just name",
  //      "something description something description something description something description something description something description "));
  //  list.add(new SkillEntity(R.drawable.mh_2, "just name"));
  //  list.add(new SkillEntity(R.drawable.mh_3, "just name",
  //      "something description something description something description something description something description something description "));
  //  list.add(new SkillEntity(R.drawable.mh_4, "just name"));
  //  list.add(new SkillEntity(R.drawable.mh_5, "just name"));
  //  list.add(new SkillEntity(R.drawable.mh_6, "just name"));
  //  return list;
  //}

  private List<ArticleBean> mockArticles() {
    String title = "忙碌的早晨要赶着梳洗换装，处理家里大人小人，总是没有办法吃顿营养的早餐吗?";
    String content =
        "现在贴心的蒸炉只要按下按钮，不需停下手边的工作，20分钟后就可从容的享用美味健康的早点哦~ 营养分析: 早餐盘提供350大卡热量，鸡蛋为优质蛋白质来原，馒头与地瓜皆可提供碳水化合物，地瓜富含纤维素且低GI值是肠道健康的好朋友~ 冷冻馒头一颗75克约200大卡；蒸煮蛋一颗65克约90大卡，蒸地瓜50克约60大卡。";

    List<ArticleBean> list = new ArrayList<>();
    list.add(new ArticleBean(ArticleBean.TYPE_NORMAL, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }));
    list.add(new ArticleBean(ArticleBean.TYPE_MULTI_JPG, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }));
    list.add(new ArticleBean(ArticleBean.TYPE_SINGLE_JPG, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }));
    list.add(new ArticleBean(ArticleBean.TYPE_NORMAL, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }));
    list.add(new ArticleBean(ArticleBean.TYPE_MULTI_JPG, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }));
    list.add(new ArticleBean(ArticleBean.TYPE_SINGLE_JPG, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }));
    list.add(new ArticleBean(ArticleBean.TYPE_NORMAL, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }));

    return list;
  }
}
