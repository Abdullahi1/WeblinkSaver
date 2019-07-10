package com.example.abdullahi.weblinksaver.service;

import com.example.abdullahi.weblinksaver.model.Web;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface MyClient {

    @GET
    Observable<Web> getWebTitle(Url String);

}
