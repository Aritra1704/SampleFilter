package com.ryandro.covid_19_tracker.service;

import com.ryandro.covid_19_tracker.model.MainResponseObj;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * To Access the API End Points for fetching
 */

public interface APIInterface {
    @GET("Coronavirus_2019_nCoV_Cases/FeatureServer/1/query?where=1%3D1&outFields=*&outSR=4326&f=json")
    Call<MainResponseObj> getAllInfoFromServer();

/*    @GET("/flytta_api/v0.1/portal/property/findserviceid/{service_id}")
    Call<DetailLocationDO> getLoactionDetail(@Path("service_id") String service_id);*/
}
