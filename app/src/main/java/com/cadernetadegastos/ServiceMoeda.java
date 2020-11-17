package com.cadernetadegastos;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ServiceMoeda {

    @Headers("Accept: application/json")
    @GET("json/{moeda}")
    Call<List<MoedaEstrangeira>> getMoeda(@Path("moeda") String moeda);
}
