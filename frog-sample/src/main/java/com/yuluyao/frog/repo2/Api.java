package com.yuluyao.frog.repo2;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {

    @GET("/events")
    Observable<ArrayList<Bean>> getEvents();


}
