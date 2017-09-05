package com.capsule.sample.func.divider;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.repo.Data;
import com.capsule.sample.repo.Repo;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * Created by wusheng on 2017/9/2.
 */

public class DividerActivity extends BaseActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Fragment fragment = DividerVerticalFragment.newInstance();
    getSupportFragmentManager().beginTransaction()
        .replace(R.id.container, fragment, "verti")
        .commit();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_divider, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.vertical:
        Fragment fragment = DividerVerticalFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, fragment, "verti")
            .commit();
        break;
      case R.id.horizontal:
        Fragment fragment1 = DividerHorizontalFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, fragment1, "hori")
            .commit();
        break;
      case R.id.grid:
        break;
      case R.id.staggered:
        break;
    }
    return true;
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_divider;
  }
}
