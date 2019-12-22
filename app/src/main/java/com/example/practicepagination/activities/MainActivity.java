package com.example.practicepagination.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.practicepagination.R;
import com.example.practicepagination.adapter.MyAdapter;
import com.example.practicepagination.model.Model;
import com.example.practicepagination.utlis.EndlessRecyclerViewScroll;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    MyAdapter myAdapter;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    private int TOTAL_PAGES = 3;

    private LinearLayoutManager linearLayoutManager;
    ArrayList<Model> arrayList = new ArrayList<>();

    private final Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }
    public void initUI(){
        recyclerView = findViewById(R.id.recycler_id);
        progressBar = findViewById(R.id.progressBar_id);

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(MainActivity.this,arrayList);
        recyclerView.setAdapter(myAdapter);

        recyclerView.addOnScrollListener(new EndlessRecyclerViewScroll() {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= TOTAL_PAGES) {
                    progressBar.setVisibility(View.VISIBLE);
                    mHandler.postDelayed(runnable, 2000);
                }
            }
        });
        dataLoad();
    }
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            dataLoad();
        }
    };
    public void dataLoad(){
        for (int i = 0; i <= 30; i++) {
            Model model = new Model();
            arrayList.add(model);
        }
        progressBar.setVisibility(View.GONE);
        myAdapter.updateAdapter(arrayList);
    }
}
