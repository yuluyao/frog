package com.yuluyao.frog.repo;

import android.content.Context;
import android.content.res.Resources;
import com.yuluyao.frog.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/5 10:36
 */
public class Repo {

  private static Repo instance;
  private static List<Data> datas = new ArrayList<>();

  private int[] drawables = {
      R.drawable.mh_14, R.drawable.mh_15, R.drawable.mh_16, R.drawable.mh_17, R.drawable.mh_18,
      R.drawable.mh_19, R.drawable.mh_20, R.drawable.mh_21, R.drawable.mh_22, R.drawable.mh_23,
      R.drawable.mh_24, R.drawable.mh_25, R.drawable.mh_26, R.drawable.mh_27, R.drawable.mh_28,
      R.drawable.mh_29, R.drawable.mh_30, R.drawable.mh_31, R.drawable.mh_32, R.drawable.mh_33,
      R.drawable.mh_34, R.drawable.mh_35, R.drawable.mh_36, R.drawable.mh_37, R.drawable.mh_38,
      R.drawable.mh_39, R.drawable.mh_40, R.drawable.mh_41, R.drawable.mh_42, R.drawable.mh_43,
      R.drawable.mh_44, R.drawable.mh_45, R.drawable.mh_46, R.drawable.mh_47, R.drawable.mh_48,
      R.drawable.mh_49, R.drawable.mh_50, R.drawable.mh_51, R.drawable.mh_52, R.drawable.mh_53,
      R.drawable.mh_54, R.drawable.mh_55, R.drawable.mh_56, R.drawable.mh_57, R.drawable.mh_58,
      R.drawable.mh_59, R.drawable.mh_60, R.drawable.mh_61, R.drawable.mh_62, R.drawable.mh_63,
      R.drawable.mh_64, R.drawable.mh_1,  R.drawable.mh_2,  R.drawable.mh_3,  R.drawable.mh_4,
      R.drawable.mh_5,  R.drawable.mh_6,  R.drawable.mh_7,  R.drawable.mh_8,  R.drawable.mh_9,
      R.drawable.mh_10, R.drawable.mh_11, R.drawable.mh_12, R.drawable.mh_13
  };

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
    int[] icons = drawables;
    for (int i = 0; i < ids.length; i++) {
      datas.add(new Data(ids[i], titles[i], contents[i], icons[i]));
    }
  }

  public Observable<List<Data>> refresh() {
    return Observable.just(datas.subList(0, 10))
        .delay(300, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public Observable<List<Data>> refresh(int count) {
    return Observable.just(datas.subList(0, count))
        .delay(300, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public Observable<List<Data>> initData(int size) {
    size = size < 0 ? 0 : size;
    size = size > datas.size() ? datas.size() : size;
    return Observable.just(datas.subList(0, size))
        .subscribeOn(Schedulers.io())
        .delay(300, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread());
  }

  public Observable<List<Data>> load(int id) {
    if (id <= 0) {
      return refresh();
    }
    List<Data> list = new ArrayList<>();
    for (int i = 0; i < datas.size(); i++) {
      if (datas.get(i).getId() == id) {
        int lastPosition = datas.size() - 1;//63
        int startPosition = i + 1;//61

        int leftCount = lastPosition - startPosition;

        if (leftCount <= 0) {//没有了

        } else if (leftCount <= 10) {//没有下一页了
          list = datas.subList(startPosition, lastPosition + 1);
        } else {//正常，下一页
          list = datas.subList(startPosition, startPosition + 10);
        }
      }
    }
    return Observable.just(list)
        .subscribeOn(Schedulers.io())
        .delay(300, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread());
  }

  //public List<Data> refreshList() {
  //  List<Data> list = new ArrayList<>();
  //  list.addAll(datas.subList(0, 10));
  //  return list;
  //}
  //
  //public List<Data> loadMore(int id) {
  //  if (id <= 0) {
  //    return refreshList();
  //  }
  //  for (int i = 0; i < datas.size(); i++) {
  //    if (datas.get(i).getId() == id) {
  //      int lastPosition = datas.size() - 1;//63
  //      int startPosition = i + 1;//61
  //
  //      int leftCount = lastPosition - startPosition;
  //
  //      if (leftCount <= 0) {
  //        return new ArrayList<>();
  //      } else if (leftCount <= 10) {
  //        return datas.subList(startPosition, lastPosition + 1);
  //      } else {
  //        return datas.subList(startPosition, startPosition + 10);
  //      }
  //    }
  //  }
  //  return null;
  //}
}
