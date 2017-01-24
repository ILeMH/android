package fr.ealen.legorafi;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;


/**
 * Created by Alexandre on 23/01/2017.
 */

public class XMLAsyncTask extends AsyncTask<String, Void, Document> {

    interface DocumentConsumer {
        void setXMLDocument(Document document);
    }

    private DocumentConsumer _consumer;

    public XMLAsyncTask(DocumentConsumer document){
        _consumer = document;
    }

    @Override
    protected Document doInBackground(String... params) {
        try {

            URL url = new URL(params[0]);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream stream = connection.getInputStream();

            try{
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            }finally {
                stream.close();
            }
        } catch (InterruptedIOException ie){
            Log.e("AsyncTask", "Interrupted");
            return null;
        }
        catch (Exception e) {
            Log.e("AsynTask","Exeption AsyncTask: ", e);
            throw new RuntimeException(e);
        }
    }

    public void onPostExecute(Document result){
        Log.e("AsyncTask", "Finished");
        _consumer.setXMLDocument(result);
    }
}
