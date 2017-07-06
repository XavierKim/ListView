package com.example.tacademy.listtest.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        ////////////////////////////////////////
        //1. xml의 요소들중 자바 코드에서 조작할 혹은 이벤트등 작용을 해야하는 구성요소들을 모두 찾는다.
        search_summary  = (TextView)findViewById(R.id.search_summary);
        searchInput     = (EditText)findViewById(R.id.searchInput);
        gridView        = (GridView)findViewById(R.id.gridView);
        imageButton     = (ImageButton)findViewById(R.id.changeBtn);
        ////////////////////////////////////////

        initUI();
    }
    public void onSearch(View view){

    }
    public void onChangeView(View view){

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
