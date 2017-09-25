package com.capsule.sample.func.multi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.capsule.trunk.anim.impl.SlideInLeftAnimator;
import com.capsule.sample.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    recyclerView.setItemAnimator(new SlideInLeftAnimator());

    recyclerView.setAdapter(adapter);

    mockArticles().subscribe(new Consumer<List<ArticleBean>>() {
      @Override public void accept(@NonNull List<ArticleBean> datas) throws Exception {
        adapter.setData(datas);
        adapter.notifyItemRangeInserted(0, datas.size());
      }
    });

  }


  private Observable<List<ArticleBean>> mockArticles() {
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

    return Observable.just(list)
        .delay(1000, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  private Observable<ArticleBean> mockOne() {
    String title = "忙碌的早晨要赶着梳洗换装，处理家里大人小人，总是没有办法吃顿营养的早餐吗?";
    String content =
        "现在贴心的蒸炉只要按下按钮，不需停下手边的工作，20分钟后就可从容的享用美味健康的早点哦~ 营养分析: 早餐盘提供350大卡热量，鸡蛋为优质蛋白质来原，馒头与地瓜皆可提供碳水化合物，地瓜富含纤维素且低GI值是肠道健康的好朋友~ 冷冻馒头一颗75克约200大卡；蒸煮蛋一颗65克约90大卡，蒸地瓜50克约60大卡。";

    return Observable.just(new ArticleBean(ArticleBean.TYPE_SINGLE_JPG, title, content,
        new int[] { R.drawable.breakfast_1, R.drawable.breakfast_2, R.drawable.breakfast_3 }))
        .delay(2000, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
