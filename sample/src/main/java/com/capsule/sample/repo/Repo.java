package com.capsule.sample.repo;

import android.content.Context;
import android.content.res.Resources;
import com.capsule.sample.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:36
 */
public class Repo {

  private static Repo instance;
  private static List<Data> datas = new ArrayList<>();

  public static Repo getInstance(Context context) {
    if (instance == null) {
      instance = new Repo(context);
    }
    return instance;
  }

  private Repo(Context context) {
    Resources res = context.getResources();
    int[] ids = res.getIntArray(R.array.data_id);
    String[] titles = res.getStringArray(R.array.data_title);
    String[] contents = res.getStringArray(R.array.data_content);
    int[] icons = res.getIntArray(R.array.data_icon);
    for (int i = 0; i < ids.length; i++) {
      datas.add(new Data(ids[i], titles[i], contents[i], icons[i]));
    }
  }

  public List<Data> refreshList() {
    List<Data> list = new ArrayList<>();
    list.addAll(datas.subList(0, 10));
    return list;
  }

  public List<Data> loadMore(int id) {
    if (id <= 0) {
      return refreshList();
    }
    for (int i = 0; i < datas.size(); i++) {
      if (datas.get(i).getId() == id) {
        int lastPosition = datas.size() - 1;//63
        int startPosition = i + 1;//61

        int leftCount = lastPosition - startPosition;

        if (leftCount <= 0) {
          return new ArrayList<>();
        } else if (leftCount <= 10) {
          return datas.subList(startPosition, lastPosition + 1);
        } else {
          return datas.subList(startPosition, startPosition + 10);
        }
      }
    }
    return null;
  }
}
