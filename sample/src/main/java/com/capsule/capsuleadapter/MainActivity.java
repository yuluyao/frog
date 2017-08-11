package com.capsule.capsuleadapter;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.capsule.library.BaseAdapter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

  private MainAdapter        adapter;
  private SwipeRefreshLayout refreshLayout;
  private RecyclerView       recyclerView;
  private DataRepo           repo;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    initRefresh();
    initRecyclerView();
    initAdapter();
    initData();
    adapter.notifyDataSetChanged();

    addHeader();
    //addFooter();
  }

  private void initRefresh() {
    refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        Observable.create(new ObservableOnSubscribe<List<SkillBean>>() {
          @Override public void subscribe(@NonNull ObservableEmitter<List<SkillBean>> e)
              throws Exception {
            List<SkillBean> data = repo.refreshList();
            e.onNext(data);
          }
        })
            .subscribeOn(Schedulers.io())
            .delay(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<SkillBean>>() {
              @Override public void onSubscribe(@NonNull Disposable d) {

              }

              @Override public void onNext(@NonNull List<SkillBean> list) {
                refreshLayout.setRefreshing(false);
                adapter.notifyRefreshCompleted(list);
              }

              @Override public void onError(@NonNull Throwable e) {

              }

              @Override public void onComplete() {

              }
            });
      }
    });
  }

  private void initRecyclerView() {
    recyclerView = (RecyclerView) findViewById(R.id.rv);
    RecyclerView.LayoutManager manager =
        new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
    recyclerView.setLayoutManager(manager);
  }

  private void initAdapter() {
    adapter = new MainAdapter(R.layout.item_test);
    recyclerView.setAdapter(adapter);

    adapter.setEmptyView(R.layout.layout_empty);
    adapter.setLoadMoreView(new CustomLoadMoreView());
    adapter.setOnLoadMoreListener(new BaseAdapter.OnLoadMoreListener() {
      @Override public void onLoadMore() {

        Observable.create(new ObservableOnSubscribe<List<SkillBean>>() {
          @Override public void subscribe(@NonNull ObservableEmitter<List<SkillBean>> e)
              throws Exception {
            List<SkillBean> data = repo.loadMore(adapter.getLastData().getId());
            e.onNext(data);
          }
        })
            .subscribeOn(Schedulers.io())
            .delay(2000, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<SkillBean>>() {
              @Override public void onSubscribe(@NonNull Disposable d) {

              }

              @Override public void onNext(@NonNull List<SkillBean> list) {
                adapter.notifyLoadMoreCompleted(list);
                //adapter.addData(list);
                //adapter.notifyItemInserted(adapter.getLastDataPosition());
                //adapter.loadMoreCompleted();
              }

              @Override public void onError(@NonNull Throwable e) {

              }

              @Override public void onComplete() {

              }
            });
      }
    });
  }

  private void initData() {
    repo = new DataRepo(this);
    //adapter.setData(repo.refreshList());
    adapter.setData(new ArrayList<SkillBean>());
  }

  private void addHeader() {
    int height = (int) (getResources().getDisplayMetrics().density * 48);
    TextView tv = new TextView(this);
    RecyclerView.LayoutParams lp =
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
    tv.setBackgroundResource(R.color.colorAccent);
    tv.setLayoutParams(lp);
    tv.setText("我是头");
    adapter.setHeader(tv);
  }

  private void addFooter() {
    int height = (int) (getResources().getDisplayMetrics().density * 48);
    TextView tv = new TextView(this);
    RecyclerView.LayoutParams lp =
        new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
    tv.setBackgroundResource(R.color.colorAccent);
    tv.setLayoutParams(lp);
    tv.setText("我是尾");
    adapter.setFooter(tv);
  }
}
