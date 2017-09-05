package com.capsule.sample.func.head;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.capsule.recy.decor.HeadDecor;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.repo.DataRepo;

/**
 * Created by wusheng on 2017/9/2.
 */

public class HeadActivity extends BaseActivity {

  private RecyclerView recyclerView;
  private HeadAdapter  adapter;
  private DataRepo     repo;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("添加头和脚");

    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head));
    //recyclerView.addItemDecoration(new FootDecor(R.layout.layout_foot));


    repo = new DataRepo(this);

    adapter = new HeadAdapter();
    recyclerView.setAdapter(adapter);
    adapter.setData(repo.refreshList());
    adapter.notifyDataSetChanged();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_head, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.item1:
        break;
      case  R.id.item2:
        break;
    }
    return true;
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_head_foot;
  }
}
