package com.example.abdullahi.weblinksaver.service;

import java.util.Collections;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import pl.droidsonroids.retrofit2.JspoonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {

    public static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            //7.tlsVersions(TlsVersion.TLS_1_0)
            .allEnabledTlsVersions()
            .allEnabledCipherSuites()
            .build();

    public static OkHttpClient newClient = httpClient
            .connectionSpecs(Collections.singletonList(spec))
            .build();


    public static Retrofit.Builder builder = new Retrofit.Builder().baseUrl("http://www.google.com/")
            .addConverterFactory(JspoonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());


    public  static Retrofit retrofit =  builder.client(newClient).build();

    public static  <S> S buildService (Class<S> serviceType){
        return retrofit.create(serviceType);
    }
}
