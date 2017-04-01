package com.multilingua.easylanguages.easylanguages.activites;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.multilingua.easylanguages.easylanguages.R;

/**
 * Created by Alexandre on 10/03/2017.
 */

public class CoursDetail extends GlobalForMenu {
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coursdetail);
        Intent vue = getIntent();
        String url = vue.getStringExtra("vue");
        WebView wb = (WebView) findViewById(R.id.coursDetail);
        wb.loadUrl(url);
    }
}
