package com.example.tacademy.listtest.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tacademy.listtest.R;

public class GridActivity extends AppCompatActivity {


    TextView search_summary;
    EditText searchInput;
    GridView gridView;
    ImageButton imageButton;
    GridAdapter gridAdapter;
    int columnCnt; // 컬럼의 수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ////////////////////////////////////////
        columnCnt       = 3;
        //1. xml의 요소들중 자바 코드에서 조작할 혹은 이벤트등 작용을 해야하는 구성요소들을 모두 찾는다.
        search_summary  = (TextView)findViewById(R.id.search_summary);
        searchInput     = (EditText)findViewById(R.id.searchInput);
        gridView        = (GridView)findViewById(R.id.gridView);
        imageButton     = (ImageButton)findViewById(R.id.changeBtn);
        //2. 그리드뷰 구성요소 세팅
        gridAdapter = new GridAdapter();
        gridView.setAdapter(gridAdapter);           //왜 getCount를 호출하는가?
        ////////////////////////////////////////

        initUI();
    }
    public void onSearch(View view){

    }
    public void onChangeView(View view){
        if(columnCnt == 3) columnCnt = 1;
        else columnCnt = 3;
        gridView.setNumColumns(columnCnt);
        gridAdapter = new GridAdapter();                //새로운 Adapter를 이용해야 뷰의 종류가 바뀐다.
        gridView.setAdapter(gridAdapter);
    }
    class GridAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 100;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null){
                if(columnCnt == 3)
                    view = GridActivity.this.getLayoutInflater().inflate(R.layout.cell_grid_layout, viewGroup, false);
                else{
                    view = GridActivity.this.getLayoutInflater().inflate(R.layout.cell_daum_search_layout, viewGroup, false);
                }
            }else{
            }
            return view;

        }
    }




























    public void initUI(){
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
