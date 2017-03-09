package com.dice.newsrss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.webkit.WebView;

/**
 * Created by Alexandre on 20/01/2017.
 */

public class showHtml extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        String annonce = i.getStringExtra("annonce");


        WebView webView = (WebView) findViewById(R.id.webView) ;
        webView.loadData(annonce, "text/html; charset=UTF-8", null);
    }

}
