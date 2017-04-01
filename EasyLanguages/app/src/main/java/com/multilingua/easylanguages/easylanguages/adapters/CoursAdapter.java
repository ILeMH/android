package com.multilingua.easylanguages.easylanguages.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multilingua.easylanguages.easylanguages.activites.CoursDetail;
import com.multilingua.easylanguages.easylanguages.R;

/**
 * Created by Alexandre on 28/02/2017.
 */

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.CoursViewHolder> {

    private String listDeCours[];

    public CoursAdapter(String[] liste)
    {
        this.listDeCours = liste;
    }


    @Override
    public CoursAdapter.CoursViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cours, parent, false);
        return new CoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoursAdapter.CoursViewHolder holder, int position) {
        String liste = listDeCours[position];
        holder.display(liste);
    }

    @Override
    public int getItemCount() {
        return listDeCours.length;
    }

    public class CoursViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView titre;
        private String URL;

        public CoursViewHolder (final View itemView)
        {
            super(itemView);

            titre = ((TextView) itemView.findViewById(R.id.titreCours));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "file:///android_asset/cours/"+URL+".html";
                    Log.d("URL DU COURS", url);
                    Intent goCours = new Intent(itemView.getContext(), CoursDetail.class);
                    goCours.putExtra("vue", url);
                    itemView.getContext().startActivity(goCours);
                }
            });
        }

        public void display(String pair)
        {
            this.URL = pair;
            this.titre.setText(URL);
            /*String[] separated = URL.split("\\.");
            this.titre.setText(separated[0]);*/
        }
    }
}
