package fr.ealen.legorafi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by Alexandre on 23/01/2017.
 */

public class RSSAdapter extends RecyclerView.Adapter<RSSAdapter.ArticleViewHolder> implements XMLAsyncTask.DocumentConsumer {

    public interface URLLoader {
        void load(String title, String link);
    }

    private final URLLoader _urlLoader;
    
    private Document _document = null;

    public RSSAdapter(URLLoader urlLoader){ _urlLoader = urlLoader; }

    @Override
    public int getItemCount() {
        if(_document != null) return _document.getElementsByTagName("item").getLength();
        else return 0;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Element item = (Element) _document.getElementsByTagName("item").item(position);
        holder.setElement(item);
    }

    @Override
    public void setXMLDocument(Document document) {
        _document = document;
        notifyDataSetChanged();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final TextView _title;

        private Element _currentElement;

        public ArticleViewHolder(final View itemView){
            super(itemView);

            _title = ((TextView) itemView.findViewById(R.id.title));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View vizw) {
                    String title = _currentElement.getElementsByTagName("title").item(0).getTextContent();
                    String link = _currentElement.getElementsByTagName("link").item(0).getTextContent();
                    _urlLoader.load(title, link);

                    final Context context = itemView.getContext();
                    Intent intent = new Intent(context, ArticleActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("link", link);
                    context.startActivity(intent);
                }
            });
        }

        public void setElement(Element element){
            _currentElement = element;
            _title.setText(element.getElementsByTagName("title").item(0).getTextContent());
        }
    }
}
