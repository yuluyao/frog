package com.yuluyao.frog.func.head;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import yuluyao.frog.decor.HeadDecor;
import com.yuluyao.frog.R;
import com.yuluyao.frog.base.BaseActivity;
import com.yuluyao.frog.repo.Data;
import com.yuluyao.frog.repo.Repo;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * Created by wusheng on 2017/9/2.
 */

public class HeadActivity extends BaseActivity {

  private RecyclerView recyclerView;
  private HeadAdapter  adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("添加头和脚");

    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head));

    adapter = new HeadAdapter();
    recyclerView.setAdapter(adapter);

    Repo.getInstance(this).refresh().subscribe(new Consumer<List<Data>>() {
      @Override public void accept(@NonNull List<Data> datas) throws Exception {
        adapter.setData(datas);
        adapter.notifyItemRangeInserted(0,datas.size());
      }
    });
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_head, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.item1:
        break;
      case R.id.item2:
        break;
    }
    return true;
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_head;
  }
}
