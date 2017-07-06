package com.example.tacademy.listtest.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.tacademy.listtest.R;
import com.example.tacademy.listtest.model.DaumSearchResultModel;

public class DetailActivity extends AppCompatActivity {


    DaumSearchResultModel.Channel.Item data;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //데이터 받기

        data = (DaumSearchResultModel.Channel.Item)getIntent().getSerializableExtra("data");
        //웹뷰 객체 설정
        webView = (WebView)findViewById(R.id.web);
        // 로딩
        webView.loadUrl(data.getLink());
        webView.setWebViewClient(new WebViewClient());

    }
}
