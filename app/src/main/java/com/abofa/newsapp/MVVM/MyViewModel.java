package com.abofa.newsapp.MVVM;

import com.abofa.newsapp.ApiResponses.ArticlesItem;
import com.abofa.newsapp.ApiResponses.NewsResponse;
import com.abofa.newsapp.ApiResponses.SourcesItem;
import com.abofa.newsapp.ApiResponses.SourcesResponse;
import com.abofa.newsapp.Constants.MyConstants;
import com.abofa.newsapp.ManagerSourcesResponses.ApiManager;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyViewModel extends ViewModel {

    MutableLiveData<ArrayList<SourcesItem>> mySources;
    MutableLiveData<ArrayList<ArticlesItem>> myArticles;

    public MyViewModel() {
        myArticles = new MutableLiveData<>();
        mySources = new MutableLiveData<>();
     }

    public void getNewsSources(){
        ApiManager.getApis()
                .getNewsSource(MyConstants.MY_API_KEY)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
                        mySources.setValue((ArrayList<SourcesItem>) response.body().getSources());
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call, Throwable t) {

                    }
                });
    }

    public void getNewsBySourceId(String sourceId){
        ApiManager.getApis()
                .getNewsBySourceId(MyConstants.MY_API_KEY,sourceId)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        myArticles.setValue((ArrayList<ArticlesItem>) response.body().getArticles());
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                    }
                });
    }

    public MutableLiveData<ArrayList<SourcesItem>> getMySources() {
        return mySources;
    }

    public MutableLiveData<ArrayList<ArticlesItem>> getMyArticles() {
        return myArticles;
    }
}
