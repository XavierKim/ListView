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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tacademy.listtest.R;
import com.example.tacademy.listtest.model.DaumSearchResultModel;
import com.example.tacademy.listtest.net.Net;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.tacademy.listtest.R.id.comment;
import static com.example.tacademy.listtest.R.id.poster;

public class SearchActivity extends RootActivity {

    SearchActivity self;
    EditText searchInput;
    ListView listView;
    SearchAdapter searchAdapter;
    // 통신후 받은 결과를 여기서 참조한다.
    ArrayList<DaumSearchResultModel.Channel.Item> items;

    ImageLoader imageLoader;
    DisplayImageOptions options;
    // 검색 결과 통계를 보여주고, 없을시 결과 없음을 표시
    TextView search_summary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this; // 자기 자신 객체인 this의 참조값을 담은 전역변수
        setContentView(R.layout.activity_search);
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
        items           = new ArrayList();
        initImageLoader();
        search_summary  = (TextView)findViewById(R.id.search_summary);
        searchInput     = (EditText)findViewById(R.id.searchInput);
        listView        = (ListView)findViewById(R.id.listView);
        searchAdapter   = new SearchAdapter();
        listView.setAdapter(searchAdapter);

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
    class ViewHolder{
        TextView name;
        TextView comment;
        ImageView poster;
    }
    class SearchAdapter extends BaseAdapter{
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
            if( view == null ){
                // 화면에 셀을 로테이션 시킬 정도양이 될때까지 계속 호출된다.
                view    = self.getLayoutInflater().inflate(R.layout.cell_daum_search_layout, viewGroup, false);
                holder  = new ViewHolder();
                // 어차피 재활용되니가 자원을 많이 사용하는 findViewById는 최초 1회만 하는것으로 정리하지
                // ViewHolder
                holder.name     = (TextView)view.findViewById(R.id.name);
                holder.comment  = (TextView)view.findViewById(comment);
                holder.poster   = (ImageView)view.findViewById(poster);
                view.setTag(holder);
            }else{
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
            holder.comment.setText( String.format("공개일:%s 출처:%s", item.getPubDate(), item.getCpname()) );
            // 이미지 세팅
            imageLoader.displayImage(item.getThumbnail(), holder.poster, options);
            return view;
        }
    }
    // 검색 진행
    public void onSearch(View view){
        // 검색을 누르면 호출
        search_summary.setText(null);
        items.clear(); // 검색 결과를 비운다
        // 키워드 추출
        final String keyword = searchInput.getText().toString();
        // daum 통신 요청
        Map<String, String> params = new HashMap<>();
        params.put("apikey", "7dc9bd9ddc918206c14fa731294074db");
        params.put("q", keyword);//"박열");
        params.put("output", "json");
        // 응답 후 화면 갱신
        showPD();
        Call<DaumSearchResultModel> res = Net.getInstance().getDaumFactoryIm().searchImage(params);
        res.enqueue(new Callback<DaumSearchResultModel>() {
            @Override
            public void onResponse(Call<DaumSearchResultModel> call, Response<DaumSearchResultModel> response) {
                if( response!=null && response.isSuccessful() ){
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
                    searchAdapter.notifyDataSetChanged();
                    searchInput.setText(null);
                }else{
                    search_summary.setText( "["+keyword+"] 검색된 결과가 없습니다." );
                }
                stopPD();
            }
            @Override
            public void onFailure(Call<DaumSearchResultModel> call, Throwable t) {
                stopPD();
            }
        });


    }
    // 한줄에 한개 => 한줄에 3개(사진 중심)
    public void onChangeView(View view){
        stopPD();
    }

}












