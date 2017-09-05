package com.capsule.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.capsule.recy.click.ItemClickListener;
import com.capsule.recy.decor.HeadDecor;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.func.click.ClickActivity;
import com.capsule.sample.func.divider.DividerActivity;
import com.capsule.sample.func.empty.EmptyActivity;
import com.capsule.sample.func.head.HeadActivity;
import com.capsule.sample.func.load.LoadActivity;
import com.capsule.sample.func.multi.MultipleActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("主页");
    initView();
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_main;
  }

  private void initView() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addOnItemTouchListener(new ItemClickListener() {
      @Override public void onItemClick(RecyclerView.ViewHolder vh, int position, View childView) {
        startByPosition(position);
      }
    });
    recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head, true));

    MainAdapter adapter = new MainAdapter();
    recyclerView.setAdapter(adapter);

    adapter.setData(mockData());
    adapter.notifyDataSetChanged();
  }

  private List<String> mockData() {
    List<String> list = new ArrayList<>();
    list.add("empty view");
    list.add("head and foot");
    list.add("load");
    list.add("click listener");
    list.add("divider");
    list.add("multiple item type");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    list.add("...");
    return list;
  }

  private void startByPosition(int position) {
    Intent intent = null;
    switch (position) {
      case 0:
        intent = new Intent(this, EmptyActivity.class);
        break;
      case 1:
        intent = new Intent(this, HeadActivity.class);
        break;
      case 2:
        intent = new Intent(this, LoadActivity.class);
        break;
      case 3:
        intent = new Intent(this, ClickActivity.class);
        break;
      case 4:
        intent = new Intent(this, DividerActivity.class);
        break;
      case 5:
        intent = new Intent(this, MultipleActivity.class);
        break;

      default:
        return;
    }
    startActivity(intent);
  }
}
