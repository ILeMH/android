package com.dice.newsrss;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by Alexandre on 20/01/2017.
 */

public class MyLongTreatmentTask extends AsyncTask<Integer, Void, String> {
    private final TextView _textView;

    public MyLongTreatmentTask(TextView textView)
    {
        _textView = textView;
    }

    @Override
    protected String doInBackground(Integer... params) {
        int seconds = params[0];

        try{ Thread.sleep(seconds*1000);}
        catch (InterruptedException ignored){}
        return "coucou";
    }

    @Override
    protected void onPostExecute(String result){
        _textView.setText(result);
    }
}
