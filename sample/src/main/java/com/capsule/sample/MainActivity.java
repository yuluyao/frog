package com.capsule.sample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.capsule.sample.multi.MultiItemActivity;
import com.capsule.sample.normal.NormalActivity;

public class MainActivity extends AppCompatActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ListView listView = (ListView) findViewById(R.id.list);
    ArrayAdapter<String> adapter =
        new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
            new String[] { "base use", "multiple item" });
    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
          case 0:
            startActivity(new Intent(MainActivity.this, NormalActivity.class));
            break;
          case 1:
            startActivity(new Intent(MainActivity.this, MultiItemActivity.class));
            break;
        }
      }
    });
  }
}
