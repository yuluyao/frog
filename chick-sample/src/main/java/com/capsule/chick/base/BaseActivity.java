package com.capsule.chick.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.capsule.chick.R;

/**
 * Created by wusheng on 2017/9/2.
 */

public abstract class BaseActivity extends AppCompatActivity {

  private Toolbar toolbar;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(onGetLayoutId());

    initToolbar();
  }

  private void initToolbar() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    if (null == toolbar) {
      return;
    }
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(true);
    toolbar.setNavigationOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        onBackPressed();
      }
    });

  }

  protected abstract int onGetLayoutId();
}
