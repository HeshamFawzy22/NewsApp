
package com.abofa.newsapp.ApiResponses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class NewsResponse {

    @SerializedName("articles")
    private List<ArticlesItem> articles;
    @SerializedName("status")
    private String status;
    @SerializedName("totalResults")
    private Long totalResults;

    public List<ArticlesItem> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesItem> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }

}
