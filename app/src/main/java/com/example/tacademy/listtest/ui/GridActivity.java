package com.example.tacademy.listtest.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.listtest.R;
import com.example.tacademy.listtest.model.DaumSearchResultModel;
import com.example.tacademy.listtest.net.Net;
import com.example.tacademy.listtest.uti.U;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tacademy.listtest.R.id.comment;
import static com.example.tacademy.listtest.R.id.poster;

public class GridActivity extends RootActivity {
    TextView search_summary;
    EditText searchInput;
    GridView gridView;
    ImageButton changeBtn;
    GridAdapter gridAdapter;
    int columnCnt; // 컬럼의 수
    ArrayList<DaumSearchResultModel.Channel.Item> items = new ArrayList();
    ImageLoader imageLoader;
    DisplayImageOptions options;
    int pageno = 1;     // 현재 페이지
    int pageCount = 0;  // 전체 총 페이지 : 통신후 세팅
    boolean isNetworking;// 통신중인가?

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        //////////////////////////////////////////////////
        columnCnt       = 3;
        //1 . xml의 요소듫중 자바 코드에서 조작할 혹은 이벤트등 작용을 해야하는 구성요소들을 모두 찾는다
        search_summary  = (TextView)findViewById(R.id.search_summary);
        searchInput     = (EditText)findViewById(R.id.searchInput);
        gridView        = (GridView)findViewById(R.id.gridView);
        changeBtn       = (ImageButton)findViewById(R.id.changeBtn);
        //2. 그리드뷰 구성요소 세팅
        gridAdapter     = new GridAdapter();
        gridView.setAdapter(gridAdapter); // -> getCount() -> getView()
        //////////////////////////////////////////////////
        //initUI();
        initImageLoader();
    }
    public void initImageLoader()                       {
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
    public void onSearch(View view)                     {
        // 검색을 누르면 호출
        search_summary.setText(null);
        items.clear(); // 검색 결과를 비운다
        netStart();
    }
    public void netStart()
    {
        isNetworking = true;
        // 키워드 추출
        final String keyword = searchInput.getText().toString();
        // daum 통신 요청
        Map<String, String> params = new HashMap<>();
        params.put("apikey", "7dc9bd9ddc918206c14fa731294074db");
        params.put("q", keyword);//"박열");
        params.put("output", "json");
        // 페이징
        params.put("result", "20");
        params.put("pageno", ""+pageno);
        // 응답 후 화면 갱신
        showPD();
        Call<DaumSearchResultModel> res = Net.getInstance().getDaumFactoryIm().searchImage(params);
        res.enqueue(new Callback<DaumSearchResultModel>() {
            @Override
            public void onResponse(Call<DaumSearchResultModel> call, Response<DaumSearchResultModel> response) {
                if( response!=null && response.isSuccessful() ){
                    // 0. 총페이지수
                    if( pageCount == 0 ) // 변동부분은 일단 미고려 다음 API는 3까지만
                        pageCount = Integer.parseInt( response.body().getChannel().getPageCount() );

                    // 1. 데이터를 옮겨 담는다
                    items.addAll(response.body().getChannel().getItem());
                    // 검색된 총 결과수 획득
                    int totalCount = Integer.parseInt(response.body().getChannel().getTotalCount());
                    int result     = items.size(); // 누적된 수
                    // 검색의 결과 처리
                    if( totalCount > 0 ){
                        search_summary.setText( "["+keyword+"] 총 검색된수(" + totalCount +") / 현재누적수 (" + result + ")" );
                    }
                    // 2. 갱신 -> ListView 갱신 => 아답터에게 알린다.
                    gridAdapter.notifyDataSetChanged();
                    // 검색어 유지
                    //searchInput.setText(null);
                }else{
                    search_summary.setText( "["+keyword+"] 검색된 결과가 없습니다." );
                }
                stopPD();
                isNetworking = false;
            }
            @Override
            public void onFailure(Call<DaumSearchResultModel> call, Throwable t) {
                stopPD();
                isNetworking = false;
            }
        });
    }
    public void onChangeView(View view)                 {
        if( columnCnt == 3)     columnCnt = 1;
        else                    columnCnt = 3;
        // 그리드뷰의 컬럼수를 조정
        gridView.setNumColumns(columnCnt);
        gridAdapter     = new GridAdapter();
        gridView.setAdapter(gridAdapter);
    }
    class ViewHolder                                    {
        TextView name;
        TextView comment;
        ImageView poster;
    }
    class GridAdapter extends BaseAdapter               {
        @Override
        public int getCount() {
            return items.size();
        }
        @Override
        public DaumSearchResultModel.Channel.Item getItem(int i) {
            return items.get(i);
        }
        @Override
        public long getItemId(int i) {
            return 0;
        }
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder holder;
            if( view == null){
                // xml -> view 객체 획득
                U.getInstance().log(i+"번 요청");
                if( columnCnt == 3 )
                    view = GridActivity.this.getLayoutInflater().inflate(R.layout.cell_grid_layout, viewGroup, false);
                else
                    view = GridActivity.this.getLayoutInflater().inflate(R.layout.cell_daum_search_layout, viewGroup, false);

                holder  = new ViewHolder();
                // 어차피 재활용되니가 자원을 많이 사용하는 findViewById는 최초 1회만 하는것으로 정리하지
                // ViewHolder
                holder.name     = (TextView)view.findViewById(R.id.name);
                if( columnCnt != 3 )
                    holder.comment  = (TextView)view.findViewById(comment);
                holder.poster   = (ImageView)view.findViewById(poster);
                view.setTag(holder);
            }else{
                U.getInstance().log(i+"번 재활용");
                holder = (ViewHolder)view.getTag();
            }
            // 데이터 획득
            DaumSearchResultModel.Channel.Item item = getItem(i);
            // 세팅
            // &lt;b&gt;
            holder.name.setText( Html.fromHtml(item.getTitle()) );
            // <b>
            holder.name.setText( Html.fromHtml(holder.name.getText().toString()) );
            // b
            // 회원 가입 또는 계속하기를 클릭하면 에어비앤비의 <font color='blue'>서비스 약관, 결제 서비스 약관,</font>
            if( columnCnt != 3 )
                holder.comment.setText( String.format("공개일:%s 출처:%s", item.getPubDate(), item.getCpname()) );
            // 이미지 세팅
            imageLoader.displayImage(item.getThumbnail(), holder.poster, options, new ImageLoadingListener(){
                @Override
                public void onLoadingStarted(String imageUri, View view) {

                }
                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                }
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    view.setVisibility(View.VISIBLE);
                }
                @Override
                public void onLoadingCancelled(String imageUri, View view) {
                }
            });

            // 목록이 마지막인가?(true) 목록을 요청하는 중인가?(false)
            if( i == items.size()-1 && !isNetworking ){
                if( pageno < pageCount ) {    // 현재 페이지가 마지막페이지-1보다 작으면
                    pageno++;   // 페이지 증가, 다음 API는 3까지만
                    netStart(); // 요청
                }
            }

            return view;
        }
    }
    public void initUI()                                {
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
    }
}
