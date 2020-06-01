package com.abofa.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.abofa.newsapp.Adapters.NewsAdapter;
import com.abofa.newsapp.ApiResponses.ArticlesItem;
import com.abofa.newsapp.ApiResponses.SourcesItem;
import com.abofa.newsapp.MVVM.MyViewModel;
import com.abofa.newsapp.base.BaseActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends BaseActivity implements NewsAdapter.OnItemClickListener {
    protected TabLayout tablayout;
    protected RecyclerView recyclerView;
    protected ProgressBar progressBar;
    protected View noData;
    NewsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    MyViewModel myViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        myViewModel = ViewModelProviders.of(this).get(MyViewModel.class);

        //getSources();
        myViewModel.getMySources().observe(this, new Observer<ArrayList<SourcesItem>>() {
            @Override
            public void onChanged(ArrayList<SourcesItem> sourcesItems) {
                progressBar.setVisibility(View.GONE);
                initTabLayout(sourcesItems);
            }
        });
        myViewModel.getMyArticles().observe(this, new Observer<ArrayList<ArticlesItem>>() {
            @Override
            public void onChanged(ArrayList<ArticlesItem> articlesItems) {
                progressBar.setVisibility(View.GONE);
                initRecyclerView(articlesItems);
            }
        });
        myViewModel.getNewsSources();

    }


    public void initRecyclerView(ArrayList<ArticlesItem> articlesItems){
        adapter = new NewsAdapter(this,articlesItems);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    /*public void getSources(){

        progressBar.setVisibility(View.VISIBLE);
        ApiManager.getApis()
                .getNewsSource(MyConstants.MY_API_KEY)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
                        progressBar.setVisibility(View.GONE);

                            List<SourcesItem> sources = response.body().getSources();

                            for (SourcesItem item : sources) {
                                Log.e("source", item.getName());
                            }
                            initTabLayout(sources);
                        }

                    @Override
                    public void onFailure(Call<SourcesResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Log.e("error",t.getMessage());
                    }
                });
    }*/
    /*public void getNewsBySourceId(String sourceId){
        ApiManager.getApis()
                .getNewsBySourceId(MyConstants.MY_API_KEY , sourceId)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        progressBar.setVisibility(View.GONE);
                        ArrayList<ArticlesItem> articlesItems = (ArrayList<ArticlesItem>) response.body().getArticles();
                        for (ArticlesItem item: articlesItems) {
                            Log.e("News" , item.getTitle());
                        }

                        NewsAdapter newsAdapter = new NewsAdapter(articlesItems);
                        recyclerView.setAdapter(newsAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                    }
                });
    }*/
    private void initTabLayout(final List<SourcesItem>sourcesItems){
        for (SourcesItem source:sourcesItems) {
            TabLayout.Tab tab = tablayout.newTab();
            tab.setText(source.getName());
            tab.setTag(source);
            tablayout.addTab(tab);
        }

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myViewModel.getNewsBySourceId(sourcesItems.get(tab.getPosition()).getId());
                //getNewsBySourceId(sourcesItems.get(tab.getPosition()).getId());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                myViewModel.getNewsBySourceId(sourcesItems.get(tab.getPosition()).getId());

            }

        });
        tablayout.getTabAt(0).select();
    }

    private void initView() {
        tablayout =  findViewById(R.id.tablayout);
        recyclerView =  findViewById(R.id.recycler_view);
        progressBar =  findViewById(R.id.progress_bar);
        noData =  findViewById(R.id.no_data_view);
    }

    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(this,NewsPage.class);
        String url = myViewModel.getMyArticles().getValue().get(pos).getUrl();
        intent.putExtra("URL" , url);
        startActivity(intent);
    }
}
