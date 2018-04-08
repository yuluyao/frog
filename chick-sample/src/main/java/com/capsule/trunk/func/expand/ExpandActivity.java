package com.capsule.trunk.func.expand;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import capsule.trunk.click.ItemClickListener;
import capsule.trunk.decor.Divider;
import com.capsule.trunk.R;
import com.capsule.trunk.base.BaseActivity;
import com.capsule.trunk.repo.Data;
import com.capsule.trunk.repo.Repo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wusheng on 2017/9/9.
 */

public class ExpandActivity extends BaseActivity {

  private RecyclerView  recyclerView;
  private ExpandAdapter adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("展开详情");
    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new Divider(2, R.color.colorAccent));
    //recyclerView.setItemAnimator(new SlideInLeftAnimator());
    recyclerView.addOnItemTouchListener(new ItemClickListener() {
      @Override public void onItemClick(RecyclerView.ViewHolder vh, int position, View childView) {
        ArtBean data = adapter.getData(position);
        data.setExpand(!data.isExpand());
        adapter.notifyItemChanged(position);
      }
    });

    adapter = new ExpandAdapter();
    recyclerView.setAdapter(adapter);

    Repo.getInstance(this).initData(30).map(new Function<List<Data>, List<ArtBean>>() {
      @Override public List<ArtBean> apply(@NonNull List<Data> datas) throws Exception {
        List<ArtBean> arts = new ArrayList<>();
        for (Data d : datas) {
          arts.add(new ArtBean(d));
        }
        return arts;
      }
    }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<ArtBean>>() {
      @Override public void accept(@NonNull List<ArtBean> artBeen) throws Exception {
        adapter.setData(artBeen);
        adapter.notifyItemRangeInserted(0, artBeen.size());
      }
    });
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_expand;
  }
}