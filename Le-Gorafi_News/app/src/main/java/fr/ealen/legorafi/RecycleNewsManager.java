package fr.ealen.legorafi;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oc.rss.fake.FakeNews;

import java.util.List;

public class RecycleNewsManager extends RecyclerView.Adapter<RecycleNewsManager.MyViewHolder> {

    // Constructeur des FakeNews Le Gorafi
    private List<FakeNews> list;
    public RecycleNewsManager(List<FakeNews> list) {
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_cell, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        FakeNews News = list.get(position);
        holder.display(News);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private FakeNews News;
        private final TextView title;

        public MyViewHolder(final View itemView) {
            super(itemView);
            title = ((TextView) itemView.findViewById(R.id.title));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Lancement de l'activité de News
                    Intent intent = new Intent(view.getContext(), NewsActivity.class);
                    intent.putExtra("title", News.title);
                    intent.putExtra("content", News.htmlContent);
                    view.getContext().startActivity(intent);
                }
            });
        }

        public void display(FakeNews News) {
            this.News = News;
            title.setText(News.title);
        }
    }
}