package com.example.tacademy.listtest.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tacademy.listtest.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ServiceActivity extends AppCompatActivity {

    ListView listView;
    String[][] data = {
            {"	1챔피언스 리그 직행	","	첼시 FC	", "1.png"},
            {"	2챔피언스 리그 직행	","	토트넘 핫스퍼 FC	", "2.png"},
            {"	3챔피언스 리그 직행	","	맨체스터 시티 FC	", "3.png"},
            {"	4챔피언스 리그 예선	","	리버풀 FC	", "4.png"},
            {"	5유로파 리그	","	아스널 FC	", "5.png"},
            {"	6유로파리그 우승으로 챔피언스 리그 직행	","	맨체스터 유나이티드 FC	", "6.png"},
            {"	7	","	에버턴 FC	", "7.png"},
            {"	8	","	사우샘프턴 FC	", "8.png"},
            {"	9	","	AFC 본머스	", "9.png"},
            {"	10	","	웨스트 브로미치 알비온 FC	", "10.png"},
            {"	11	","	웨스트햄 유나이티드 FC	", "11.png"},
            {"	12	","	레스터 시티 FC	", "12.png"},
            {"	13	","	스토크 시티 FC	", "13.png"},
            {"	14	","	크리스탈 팰리스 FC	", "14.png"},
            {"	15	","	스완지 시티 AFC	", "15.png"},
            {"	16	","	번리 FC	", "16.png"},
            {"	17	","	왓포드 FC	", "17.png"},
            {"	18강등 직행	","	헐 시티 AFC	", "18.png"},
            {"	19강등 직행	","	미들즈브러 FC	", "19.png"},
            {"	20강등 직행	","	선덜랜드 AFC	", "20.png"},

    };
    MyAdapter myAdapter;
    /*
       - ListView : 대량의 데이터를 표현하는 뷰
       - Custom Cell(통칭) : 데이터 한개를 표현하는 뷰
       - Data : 표현하고자 하는 정보
       - Adapter : 리스트뷰와 데이터를 연결해 주는 브릿지 역활 및 화면갱신, 처리등 담당

    */
    // 네트워크 이미지 처리 오픈 소스
    ImageLoader imageLoader;
    DisplayImageOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        // ===================================================================
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ===================================================================
        initImageLoader();
        listView = (ListView)findViewById(R.id.listView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }
    public void initImageLoader()
    {
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                //.bitmapConfig(Bitmap.Config.RGB_565)
                //.bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        imageLoader = ImageLoader.getInstance();
        // 컨피그 구성
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this.getBaseContext()).build();
        //ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader.init(configuration);
    }
    class MyAdapter extends BaseAdapter{
        // 데이터가 몇개지?
        @Override
        public int getCount() {
            return data.length;
        }
        // 특정 인덱스의 아아템 획득
        @Override
        public String[] getItem(int i) {
            return data[i];
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        // 특정 데이터를 화면에 보일려고 한다. cell을 구성해서 리턴해라
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            // xml로 정의된 레이아웃(cell_epl_layout)을 자바코드에[서 사용하는 View로 생성
            // LayoutInfalter
            if( view == null ){
                // 최초 사용 : 최초에는 커스텀셀(뷰)가 한개로 없어서 재사용이 않됨 그래서 재사용할수 잇는
                // 물량까지 채워질때까지 계속 생성한다.
                // true: 셀을 자식으로 추가
                // false:셀을 LayoutParam만 참조
                view = ServiceActivity.this.getLayoutInflater().inflate(R.layout.cell_epl_layout, viewGroup, false);
            }

            // 조작
            // 제목, 내용
            TextView name = (TextView)view.findViewById(R.id.name);
            TextView comment = (TextView)view.findViewById(R.id.comment);
            final ImageView poster = (ImageView)view.findViewById(R.id.poster);
            // 현재 셀의 데이터 획득
            String[] item = getItem(i);
            // 데이터 세팅
            name.setText(item[1].trim());
            comment.setText(item[0].trim());
            // 이미지 세팅
            imageLoader.displayImage("http://52.79.194.20:3000/images/"+item[2], poster, options, new ImageLoadingListener(){
                @Override
                public void onLoadingStarted(String imageUri, View view) {
                }
                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    // 이미지를 비운다
                    poster.setImageDrawable(null);
                }
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                }
                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                }
            });
            return view;
        }
    }

}



















