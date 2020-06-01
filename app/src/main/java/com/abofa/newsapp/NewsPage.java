package com.abofa.newsapp;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = getIntent().getStringExtra("URL");
        WebView webView;

        webView = new WebView(this);
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(url);
        setContentView(webView);
    }
}
