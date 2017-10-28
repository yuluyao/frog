package com.capsule.sample.func.level;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import com.capsule.recy.Adapter;
import com.capsule.recy.level.TreeEntity;
import com.capsule.sample.R;
import com.capsule.sample.base.BaseActivity;
import com.capsule.sample.func.load.LoadAdapter;
import com.capsule.sample.repo.Data;
import com.capsule.sample.repo.Repo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LevelActivity extends BaseActivity {

  private LevelAdapter       adapter;
  private SwipeRefreshLayout refreshLayout;
  private RecyclerView       recyclerView;
  private Repo               repo;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setTitle("多级列表");
    initRefresh();
    initRecyclerView();
    initAdapter();
    initData();
    adapter.notifyDataSetChanged();
  }

  @Override protected int onGetLayoutId() {
    return R.layout.activity_load;
  }

  private void initRefresh() {
    refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
    refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        repo.refresh()
            .delay(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Data>>() {
              @Override public void accept(@NonNull List<Data> datas) throws Exception {
                refreshLayout.setRefreshing(false);
                adapter.notifyRefreshCompleted(datas);
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
    adapter = new LevelAdapter();
    recyclerView.setAdapter(adapter);

    adapter.setOnLoadMoreListener(new Adapter.OnLoadMoreListener() {
      @Override public void onLoadMore() {
        repo.load(adapter.getLastData().getId())
            .delay(300, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Data>>() {
              @Override public void accept(@NonNull List<Data> datas) throws Exception {
                adapter.notifyLoadMoreCompleted(datas);
              }
            });
      }
    });
  }

  private void initData() {
    repo = Repo.getInstance(this);
    repo.refresh().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<Data>>() {
      @Override public void accept(@NonNull List<Data> datas) throws Exception {
        adapter.notifyRefreshCompleted(datas);
      }
    });
  }

  private List<Bean> mockData() {
    List<String> area = getAreas();
    for (String s : area) {
      Bean tree = new Bean();
      if (s.startsWith("一")) {
        tree.setName(s);
      }
    }
    return null;
  }

  private List<String> getAreas() {
    List<String> areas = new ArrayList<>();
    areas.add("一类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("一类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("一类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("二类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    areas.add("三类 - hello");
    return areas;
  }

  private void parse() throws JSONException {
    String json = getJson("tmp.json");
    JSONArray proArray = new JSONArray(json);
    for (int i = 0; i < proArray.length(); i++) {
      JSONObject proObj = proArray.getJSONObject(i);
      // pro
      Bean pro = new Bean();
      pro.setName(proObj.optString("name"));
      List<Bean> cityList = getChild(proObj, pro);
      pro.setChildren(cityList);
      //JSONArray cityArray = proObj.getJSONArray("sub");
      //for (int j = 0; j < cityArray.length(); j++) {
      //  JSONObject cityObj = cityArray.getJSONObject(i);
      //  // city
      //  Bean city = new Bean();
      //  city.setName(cityObj.optString("name"));
      //  city.setParent(pro);
      //}
    }

  }


  //读取孩子，设置父亲，返回孩子列表
  private List<Bean> getChild(JSONObject parentObj,Bean parent) throws JSONException {
    List<Bean> childList = new ArrayList<>();

    JSONArray childArray = parentObj.getJSONArray("sub");
    for (int i = 0; i < childArray.length(); i++) {
      JSONObject childObj = childArray.getJSONObject(i);

      Bean child = new Bean();
      child.setName(childObj.optString("name"));
      child.setParent(parent);
      childList.add(child);
    }
    return childList;
  }

  public String getJson(String fileName) {
    StringBuilder stringBuilder = new StringBuilder();
    try {
      AssetManager assetManager = getAssets();
      BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
      String line;
      while ((line = bf.readLine()) != null) {
        stringBuilder.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return stringBuilder.toString();
  }
}

