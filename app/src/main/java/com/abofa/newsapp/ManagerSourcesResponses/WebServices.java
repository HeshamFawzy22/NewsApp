package com.abofa.newsapp.ManagerSourcesResponses;

import com.abofa.newsapp.ApiResponses.NewsResponse;
import com.abofa.newsapp.ApiResponses.SourcesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {

    @GET("sources")
    Call<SourcesResponse> getNewsSource(@Query("apiKey") String apiKey);


    @GET("everything")
    Call<NewsResponse> getNewsBySourceId(@Query("apiKey") String apiKey ,
                                         @Query("sources") String sources);
}
