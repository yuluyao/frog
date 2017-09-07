package com.capsule.sample.func.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.capsule.recy.anim.CapItemAnimator;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.repo.Data;
import com.capsule.sample.repo.Repo;
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
    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    CapItemAnimator animator = new CapItemAnimator();
    animator.setAddDuration(1000);
    recyclerView.setItemAnimator(animator);

    adapter = new AnimAdapter();
    recyclerView.setAdapter(adapter);

    Repo.getInstance(this)
        .refresh()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Data>>() {
          @Override public void accept(@NonNull List<Data> datas) throws Exception {
            adapter.notifyRefreshCompleted(datas);
          }
        });
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_anim;
  }
}
