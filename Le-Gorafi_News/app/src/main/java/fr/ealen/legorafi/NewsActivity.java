package fr.ealen.legorafi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class NewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Utilisation de la WebView
        WebView webView = (WebView)findViewById(R.id.webView);

        // Utilisation des Extra
        Intent intent = getIntent();
        String Title = intent.getStringExtra("title");
        String HTML = intent.getStringExtra("content");

        // Set title
        this.setTitle(Title);
        // Load Data, au format HTML
        webView.loadData(HTML, "text/html; charset=UTF-8", null);
    }
}
