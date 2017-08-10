package com.capsule.capsuleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.capsule.library.BaseAdapter;

public class MainActivity extends AppCompatActivity {

  private MainAdapter  adapter;
  private RecyclerView recyclerView;
  private DataRepo     repo;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initRecyclerView();
    initAdapter();
    initData();
    adapter.notifyDataSetChanged();

    addHeader();
    addFooter();

  }

  private void initRecyclerView() {
    recyclerView = (RecyclerView) findViewById(R.id.rv);
    RecyclerView.LayoutManager manager =
        new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
    recyclerView.setLayoutManager(manager);
  }

  private void initAdapter() {
    adapter = new MainAdapter(R.layout.item_test);
    adapter.setOnLoadMoreListener(new BaseAdapter.OnLoadMoreListener() {
      @Override public void onLoadMore() {
        repo.loadMore(adapter.getLastData().getIconRes());
      }
    });
    recyclerView.setAdapter(adapter);
  }

  private void initData() {
    repo = new DataRepo(this);
    adapter.setData(repo.refreshList());
  }

  private void addHeader() {
    TextView tv = new TextView(this);
    RecyclerView.LayoutParams lp =
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    tv.setLayoutParams(lp);
    tv.setText("我是头");
    adapter.setHeader(tv);
  }

  private void addFooter() {
    TextView tv = new TextView(this);
    RecyclerView.LayoutParams lp =
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);
    tv.setLayoutParams(lp);
    tv.setText("我是尾");
    adapter.setFooter(tv);
  }
}
