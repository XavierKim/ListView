package com.example.tacademy.listtest.uti;

import android.util.Log;

/**
 * 싱글톤 객체로서, 이 앱에서 객체가 1개 생성되는 클래스
 * 용도 : 유틸리티 용도로 개발을 위해 생성
 */
public class U {
    private static U ourInstance = new U();

    public static U getInstance() {
        return ourInstance;
    }

    private U() {
    }

    final String TAG = "1";
    public void log(String msg)
    {
        Log.d(TAG,msg);
    }
}
