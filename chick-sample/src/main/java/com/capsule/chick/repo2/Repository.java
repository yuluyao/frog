package com.capsule.chick.repo2;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private static ArrayList<Bean> list = new ArrayList<>();

    public static void init() {
        OkHttpClient client = new OkHttpClient();
        client = client.newBuilder().addNetworkInterceptor(new StethoInterceptor()).build();
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("https://api.github.com").addConverterFactory(
                GsonConverterFactory.create(new GsonBuilder().create()))
                                   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                   .client(client).build();

        Api api = retrofit.create(Api.class);
        api.getEvents().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
           .subscribe(new Observer<ArrayList<Bean>>() {
               @Override
               public void onSubscribe(Disposable d) {

               }

               @Override
               public void onNext(ArrayList<Bean> beans) {
                   list.clear();
                   list.addAll(beans);
               }

               @Override
               public void onError(Throwable e) {

               }

               @Override
               public void onComplete() {

               }
           });
    }

    public static List<Bean> refreshData() {
        return list.subList(0, 9);
    }

    public static List<Bean> loadMore(String id) {
        int start = 0;
        for (int i = 0; i < list.size(); i++) {
            if (id.equals(list.get(i).getId())) {
                start = i + 1;
            }
        }
        return list.subList(start, start + 10);
    }


}
