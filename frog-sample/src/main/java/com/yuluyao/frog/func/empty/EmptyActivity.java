package com.yuluyao.frog.func.empty;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import yuluyao.frog.click.EmptyClickListener;
import yuluyao.frog.decor.EmptyDecor;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import com.yuluyao.frog.R;
import com.yuluyao.frog.base.BaseActivity;
import com.yuluyao.frog.repo.Data;
import com.yuluyao.frog.repo.Repo;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by wusheng on 2017/9/2.
 */

public class EmptyActivity extends BaseActivity {

  private RecyclerView recyclerView;
  private EmptyAdapter adapter;
  private Repo repo;


  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("加载为空");
    repo = Repo.getInstance(this);

    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new EmptyDecor(R.layout.layout_empty));
    recyclerView.addOnItemTouchListener(new EmptyClickListener() {
      @Override public void onEmptyClick() {
        Toast.makeText(EmptyActivity.this, "点击任意地方刷新...", Toast.LENGTH_SHORT).show();
        repo.refresh()
            .delay(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Data>>() {
              @Override public void accept(@NonNull List<Data> datas) throws Exception {
                adapter.notifyRefreshCompleted(datas);
              }
            });
      }
    });

    adapter = new EmptyAdapter();
    recyclerView.setAdapter(adapter);

  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_empty;
  }
}
