package com.example.praty.redditdota;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView=(WebView) findViewById(R.id.my_web_view);
        final ProgressBar mProgress= (ProgressBar) findViewById(R.id.webProgress);

        webView.getSettings().setJavaScriptEnabled(true);
        Intent intent=getIntent();
        mProgress.setVisibility(View.VISIBLE);
        webView.loadUrl(intent.getStringExtra("url"));

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgress.setVisibility(View.GONE);
            }
        });

    }
}
