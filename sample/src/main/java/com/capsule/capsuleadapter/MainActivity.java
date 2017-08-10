package com.capsule.capsuleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv);
    RecyclerView.LayoutManager manager =
        new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

    recyclerView.setLayoutManager(manager);

    MainAdapter adapter = new MainAdapter(R.layout.item_test);
    DataRepo repo = new DataRepo(this);

    adapter.setData(repo.refreshList());
    recyclerView.setAdapter(adapter);

    adapter.notifyDataSetChanged();
  }




}
