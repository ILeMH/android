package fr.ealen.legorafi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by sch-a on 24/01/2017.
 */

public class ShareActivity extends Activity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);

        String text = getIntent().getStringExtra(Intent.EXTRA_TEXT);

        ((TextView) findViewById(R.id.textView)).setText(text);
    }
}
