package com.example.tacademy.listtest.net;

import com.example.tacademy.listtest.model.DaumSearchResultModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 통신 API를 선언한다.구현(X)
 */

public interface DaumFactoryIm
{
    // 이미지 검색
    @GET("search/image")
    //Call<List<User>> login(@Query("userEmail") String userEmail, @Query("userPwd") String userPwd);
    Call<DaumSearchResultModel> searchImage(@QueryMap Map<String, String> options);
}









