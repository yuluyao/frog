package com.capsule.sample.func.click;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.capsule.recy.click.ItemClickListener;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.repo.DataRepo;

/**
 * Created by wusheng on 2017/9/2.
 */

public class ClickActivity extends BaseActivity {

  private RecyclerView recyclerView;
  private ClickAdapter adapter;

  private DataRepo repo;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("点击事件");
    recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addOnItemTouchListener(new ItemClickListener() {
      @Override public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        Toast.makeText(ClickActivity.this, "点击："+position, Toast.LENGTH_SHORT).show();
      }
    });

    repo = new DataRepo(this);

    adapter = new ClickAdapter();
    recyclerView.setAdapter(adapter);
    adapter.notifyRefreshCompleted(repo.refreshList());
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_click;
  }



}
