package com.capsule.sample.func.divider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.capsule.recy.decor.Divider;
import com.capsule.sample.R;
import com.capsule.sample.repo.Data;
import com.capsule.sample.repo.Repo;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 20:06
 */
public class DividerStaggeredVerticalFragment extends Fragment {

  public static DividerStaggeredVerticalFragment newInstance() {
    Bundle args = new Bundle();
    DividerStaggeredVerticalFragment fragment = new DividerStaggeredVerticalFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_divider_staggered_vertical, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
    recyclerView.setLayoutManager(
        new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
    recyclerView.addItemDecoration(new Divider(2, R.color.colorAccent));

    final DividerStaggeredVerticalAdapter adapter = new DividerStaggeredVerticalAdapter();
    recyclerView.setAdapter(adapter);

    Repo.getInstance(getContext()).refresh().subscribe(new Consumer<List<Data>>() {
      @Override public void accept(@NonNull List<Data> datas) throws Exception {
        adapter.notifyRefreshCompleted(datas);
      }
    });
  }
}
