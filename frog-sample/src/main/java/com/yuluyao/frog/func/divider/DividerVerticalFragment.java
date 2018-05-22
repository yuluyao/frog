package com.yuluyao.frog.func.divider;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import yuluyao.frog.decor.Divider;
import yuluyao.frog.swipe.SwipeCallback;
import com.yuluyao.frog.R;
import com.yuluyao.frog.repo.Data;
import com.yuluyao.frog.repo.Repo;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 16:00
 */
public class DividerVerticalFragment extends Fragment {

  public static DividerVerticalFragment newInstance() {
    Bundle args = new Bundle();
    DividerVerticalFragment fragment = new DividerVerticalFragment();
    fragment.setArguments(args);
    return fragment;
  }


  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_divider_vertical, container, false);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.addItemDecoration(new Divider(1f, R.color.colorAccent));
    setSwipe(recyclerView);

    final DividerVerticalAdapter adapter = new DividerVerticalAdapter();
    recyclerView.setAdapter(adapter);

    Repo.getInstance(getContext()).refresh().subscribe(new Consumer<List<Data>>() {
      @Override public void accept(@NonNull List<Data> datas) throws Exception {
        adapter.notifyRefreshCompleted(datas);
      }
    });
  }

  private void setSwipe(RecyclerView recyclerView) {
    ItemTouchHelper helper = new ItemTouchHelper(new SwipeCallback());
    helper.attachToRecyclerView(recyclerView);
  }

}
