package com.capsule.trunk.func.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import capsule.trunk.anim.impl.SlideInLeftAnimator;
import capsule.trunk.click.ItemClickListener;
import capsule.trunk.decor.Divider;
import com.capsule.trunk.R;
import com.capsule.trunk.base.BaseActivity;
import com.capsule.trunk.repo.Data;
import com.capsule.trunk.repo.Repo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/7 14:17
 */
public class AnimActivity extends BaseActivity {

  private RecyclerView recyclerView;
  private AnimAdapter  adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("动画");
    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new Divider(2, R.color.colorAccent));
    recyclerView.setItemAnimator(new SlideInLeftAnimator());
    recyclerView.addOnItemTouchListener(new ItemClickListener() {
      @Override public void onItemClick(RecyclerView.ViewHolder vh, int position, View childView) {

      }
    });


    adapter = new AnimAdapter();
    recyclerView.setAdapter(adapter);

    Repo.getInstance(this)
        .initData(30)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Data>>() {
          @Override public void accept(@NonNull List<Data> datas) throws Exception {
            adapter.setData(datas);
            adapter.notifyItemRangeInserted(0,datas.size());
          }
        });
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_anim;
  }
}
