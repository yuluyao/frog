package com.capsule.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.capsule.recy.click.ItemClickListener;
import com.capsule.recy.decor.EmptyDecor;
import com.capsule.recy.decor.HeadDecor;
import com.capsule.sample.multi.MultiItemActivity;
import com.capsule.sample.normal.NormalActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //ListView listView = (ListView) findViewById(R.id.list);
    //ArrayAdapter<String> adapter =
    //    new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
    //        new String[] { "base use", "multiple item" });
    //listView.setAdapter(adapter);
    //listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //  @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    //    switch (position) {
    //      case 0:
    //        startActivity(new Intent(MainActivity.this, NormalActivity.class));
    //        break;
    //      case 1:
    //        startActivity(new Intent(MainActivity.this, MultiItemActivity.class));
    //        break;
    //    }
    //  }
    //});

    initView();
  }

  private void initView() {
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.addOnItemTouchListener(new ItemClickListener(recyclerView) {
      @Override public void onItemClick(RecyclerView.ViewHolder vh, int position) {
        startByPosition(position);
      }
    });
    recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head,true));

    MainAdapter adapter = new MainAdapter();
    recyclerView.setAdapter(adapter);


    adapter.setData(mockData());
    adapter.notifyDataSetChanged();
  }

  private List<String> mockData() {
    List<String> list = new ArrayList<>();
    list.add("normal use");
    list.add("multiple item");
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
        intent = new Intent(this, NormalActivity.class);
        break;
      case 1:
        intent = new Intent(this, MultiItemActivity.class);
        break;

      default:
        return;
    }
    startActivity(intent);
  }
}
