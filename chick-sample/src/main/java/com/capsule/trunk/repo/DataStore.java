package com.capsule.trunk.repo;

import com.capsule.trunk.R;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/9/19 20:53
 */
public class DataStore {

  private static DataStore  dataStore;
  private        List<Head> heads;
  private        List<Card> cards;

  private List<Integer> ids = new ArrayList<>();

  private List<Integer> icons = new ArrayList<>();
  private List<Integer> pics  = new ArrayList<>();

  private List<String> names = new ArrayList<>();

  private List<String> descriptions = new ArrayList<>();

  private DataStore() {
    init();
  }

  public static DataStore getInstance() {
    if (dataStore == null) {
      dataStore = new DataStore();
    }
    return dataStore;
  }

  private Observable<List<Head>> refreshHead() {
    return Observable.just(heads.subList(0, 10))
        .delay(300, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  private Observable<List<Card>> refreshCard() {
    return Observable.just(cards.subList(0, 10))
        .delay(300, TimeUnit.MILLISECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }



  private void init() {

    icons.add(R.mipmap.head_001);
    icons.add(R.mipmap.head_002);
    icons.add(R.mipmap.head_003);
    icons.add(R.mipmap.head_004);
    icons.add(R.mipmap.head_005);
    icons.add(R.mipmap.head_006);
    icons.add(R.mipmap.head_007);
    icons.add(R.mipmap.head_008);
    icons.add(R.mipmap.head_009);
    icons.add(R.mipmap.head_010);
    icons.add(R.mipmap.head_011);
    icons.add(R.mipmap.head_012);
    icons.add(R.mipmap.head_013);
    icons.add(R.mipmap.head_014);
    icons.add(R.mipmap.head_015);
    icons.add(R.mipmap.head_016);
    icons.add(R.mipmap.head_017);
    icons.add(R.mipmap.head_018);
    icons.add(R.mipmap.head_019);
    icons.add(R.mipmap.head_020);
    icons.add(R.mipmap.head_021);
    icons.add(R.mipmap.head_022);
    icons.add(R.mipmap.head_023);
    icons.add(R.mipmap.head_024);
    icons.add(R.mipmap.head_025);
    icons.add(R.mipmap.head_026);
    icons.add(R.mipmap.head_027);
    icons.add(R.mipmap.head_028);
    icons.add(R.mipmap.head_029);
    icons.add(R.mipmap.head_030);
    icons.add(R.mipmap.head_031);
    icons.add(R.mipmap.head_032);
    icons.add(R.mipmap.head_033);
    icons.add(R.mipmap.head_034);
    icons.add(R.mipmap.head_035);
    icons.add(R.mipmap.head_036);
    icons.add(R.mipmap.head_037);
    icons.add(R.mipmap.head_038);
    icons.add(R.mipmap.head_039);
    icons.add(R.mipmap.head_040);
    icons.add(R.mipmap.head_041);
    icons.add(R.mipmap.head_042);
    icons.add(R.mipmap.head_043);
    icons.add(R.mipmap.head_044);
    icons.add(R.mipmap.head_045);
    icons.add(R.mipmap.head_046);
    icons.add(R.mipmap.head_047);
    icons.add(R.mipmap.head_048);

    pics.add(R.mipmap.card_01);
    pics.add(R.mipmap.card_02);
    pics.add(R.mipmap.card_03);
    pics.add(R.mipmap.card_04);
    pics.add(R.mipmap.card_05);
    pics.add(R.mipmap.card_06);
    pics.add(R.mipmap.card_07);
    pics.add(R.mipmap.card_08);
    pics.add(R.mipmap.card_09);
    pics.add(R.mipmap.card_10);
    pics.add(R.mipmap.card_11);
    pics.add(R.mipmap.card_12);
    pics.add(R.mipmap.card_13);
    pics.add(R.mipmap.card_14);
    pics.add(R.mipmap.card_15);
    pics.add(R.mipmap.card_16);
    pics.add(R.mipmap.card_17);
    pics.add(R.mipmap.card_18);
    pics.add(R.mipmap.card_19);
    pics.add(R.mipmap.card_20);
    pics.add(R.mipmap.card_21);
    pics.add(R.mipmap.card_22);
    pics.add(R.mipmap.card_23);
    pics.add(R.mipmap.card_24);
    pics.add(R.mipmap.card_25);
    pics.add(R.mipmap.card_26);
    pics.add(R.mipmap.card_27);
    pics.add(R.mipmap.card_28);
    pics.add(R.mipmap.card_29);
    pics.add(R.mipmap.card_30);
    pics.add(R.mipmap.card_31);
    pics.add(R.mipmap.card_32);
    pics.add(R.mipmap.card_33);
    pics.add(R.mipmap.card_34);
    pics.add(R.mipmap.card_35);
    pics.add(R.mipmap.card_36);
    pics.add(R.mipmap.card_37);
    pics.add(R.mipmap.card_38);
    pics.add(R.mipmap.card_39);
    pics.add(R.mipmap.card_40);
    pics.add(R.mipmap.card_41);
    pics.add(R.mipmap.card_42);
    pics.add(R.mipmap.card_43);
    pics.add(R.mipmap.card_44);
    pics.add(R.mipmap.card_45);
    pics.add(R.mipmap.card_46);
    pics.add(R.mipmap.card_47);
    pics.add(R.mipmap.card_48);

    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");
    names.add("刘备");

    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");
    descriptions.add("askhgogaph");

    for (int i = 1; i <= 48; i++) {
      ids.add(i);
    }

    heads = new ArrayList<>();
    cards = new ArrayList<>();
    for (int i = 1; i <= 48; i++) {
      heads.add(new Head(ids.get(i), icons.get(i), names.get(i), descriptions.get(i)));
      cards.add(new Card(ids.get(i), pics.get(i), names.get(i), descriptions.get(i)));
    }
  }
}
