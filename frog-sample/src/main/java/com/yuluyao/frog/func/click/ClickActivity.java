//package com.yuluyao.frog.func.click;
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.Toast;
//import com.yuluyao.frog.R;
//import com.yuluyao.frog.base.BaseActivity;
//import com.yuluyao.frog.repo.Data;
//import com.yuluyao.frog.repo.Repo;
//
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import io.reactivex.annotations.NonNull;
//import io.reactivex.functions.Consumer;
//import yuluyao.frog.click.FrogClickListener;
//
//import java.util.List;
//
///**
// * Created by wusheng on 2017/9/2.
// */
//
//public class ClickActivity extends BaseActivity {
//
//  private RecyclerView recyclerView;
//  private ClickAdapter adapter;
//
//  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
//    super.onCreate(savedInstanceState);
//    setTitle("点击事件");
//    recyclerView = (RecyclerView) findViewById(R.id.recycler);
//    recyclerView.setLayoutManager(new LinearLayoutManager(this));
//    recyclerView.addOnItemTouchListener(new FrogClickListener() {
//      @Override public void onItemClicked(int position) {
//        Toast.makeText(ClickActivity.this, "点击 item" + position, Toast.LENGTH_SHORT).show();      }
//    });
//
//    adapter = new ClickAdapter();
//    recyclerView.setAdapter(adapter);
//    Repo.getInstance(this).refresh().subscribe(new Consumer<List<Data>>() {
//      @Override public void accept(@NonNull List<Data> datas) throws Exception {
////        adapter.notifyRefreshCompleted(datas);
//      }
//    });
//  }
//
//  @Override protected int onGetLayoutId() {
//    return R.layout.activity_click;
//  }
//
//  //Toast.makeText(ClickActivity.this, "点击："+position, Toast.LENGTH_SHORT).show();
//}
