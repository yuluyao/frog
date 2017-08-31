package com.capsule.sample.multi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.capsule.sample.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/31 12:03
 */
public class MultiItemActivity extends AppCompatActivity {

  private RecyclerView       recyclerView;
  private MultiSampleAdapter adapter;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_multi_item);
    recyclerView = (RecyclerView) findViewById(R.id.rv);
    adapter = new MultiSampleAdapter();
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    recyclerView.setLayoutManager(layoutManager);
    recyclerView.setAdapter(adapter);
    adapter.setData(mockData());
    adapter.notifyDataSetChanged();
  }

  private List<SkillEntity> mockData() {
    List<SkillEntity> list = new ArrayList<>();
    list.add(new SkillEntity(R.drawable.mh_1, "just name",
        "something description something description something description something description something description something description "));
    list.add(new SkillEntity(R.drawable.mh_2, "just name"));
    list.add(new SkillEntity(R.drawable.mh_3, "just name",
        "something description something description something description something description something description something description "));
    list.add(new SkillEntity(R.drawable.mh_4, "just name"));
    list.add(new SkillEntity(R.drawable.mh_5, "just name"));
    list.add(new SkillEntity(R.drawable.mh_6, "just name"));

    return list;
  }
}
