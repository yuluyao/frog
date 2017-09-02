package com.capsule.sample.func.head_foot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.capsule.recy.decor.HeadDecor;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.repo.DataRepo;

/**
 * Created by wusheng on 2017/9/2.
 */

public class HeadAndFootActivity extends BaseActivity {

  private RecyclerView       recyclerView;
  private HeadAndFootAdapter adapter;
  private DataRepo           repo;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("添加头和脚");

    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head));

    repo = new DataRepo(this);

    adapter = new HeadAndFootAdapter();
    recyclerView.setAdapter(adapter);
    adapter.setData(repo.refreshList());
    adapter.notifyDataSetChanged();
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_head_foot;
  }
}
