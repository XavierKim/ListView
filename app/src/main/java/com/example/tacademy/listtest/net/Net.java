package com.example.tacademy.listtest.net;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Tacademy on 2017-06-30.
 */
public class Net {
    // =========================================================
    private static Net ourInstance = new Net();
    public static Net getInstance() {
        return ourInstance;
    }
    private Net() {}
    // =========================================================
    // retrofit 생성
    private Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://apis.daum.net") // 기본 도메인 설정
                .addConverterFactory(GsonConverterFactory.create()) // 응답데이터를 json 자동 변환
                .build();

    public Retrofit getRetrofit() {
        return retrofit;
    }
    // =========================================================
    // API 담당 인터페이스 생성
    // API 담당 인터페이스의 객체를 생성
    DaumFactoryIm daumFactoryIm;
    // 객체를 리턴해 주는 getter 준비
    public DaumFactoryIm getDaumFactoryIm()
    {
        if( daumFactoryIm == null ){
            // 인터페이스로 정의된 메소드를 사용할수 있게 객체화 시켜준다
            daumFactoryIm = retrofit.create(DaumFactoryIm.class);
        }
        return daumFactoryIm;
    }
}












