package com.example.tacademy.listtest.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * 내가 만드는 모든 액티비티들의 부모
 * 모든 액티비티가 가져야할 공통 사항을 구현해 둔다
 * ex) 진행율을 표시하는 프로그레스, 변수
 */

public class RootActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    ProgressDialog pd;
    public void showPD()
    {
        if( pd == null ){
            pd = new ProgressDialog(this);
            pd.setCancelable(false); // 임의 취소 불가
            pd.setMessage(".. loading ..");
        }
        pd.show();
    }
    public void stopPD(){
        if( pd != null && pd.isShowing() ){
            pd.dismiss();
            //pd = null;
        }
    }

}
