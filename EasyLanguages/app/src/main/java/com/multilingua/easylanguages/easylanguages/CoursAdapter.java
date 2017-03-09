package com.multilingua.easylanguages.easylanguages;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexandre on 28/02/2017.
 */

public class CoursAdapter extends RecyclerView.Adapter<CoursAdapter.CoursViewHolder> {


    private String listDeCours[];

    public CoursAdapter(String[] liste)
    {
        this.listDeCours = liste;
    }
    /*private final List<Pair<String, String>> listeDeCours = Arrays.asList(
            Pair.create("Premier cours", "Explication du cours"),
            Pair.create("Deuxieme cours", "Explication du cours"),
            Pair.create("Troisième", "Cours fini"),
            Pair.create("Quatrième", "Une autre cours fini"),
            Pair.create("Cinquième", "encore !"),
            Pair.create("Dernier","Dernier cours fini")
    );*/


    @Override
    public CoursAdapter.CoursViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cours, parent, false);
        return new CoursViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoursAdapter.CoursViewHolder holder, int position) {
        //Pair<String, String> pair = listeDeCours.get(position);
        String liste = listDeCours[position];
        holder.display(liste);
    }

    @Override
    public int getItemCount() {
        //return listeDeCours.size();
        return listDeCours.length;
    }

    public class CoursViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView titre;
        //private final TextView explications;

        //private Pair<String, String> currentPair;
        private String currentPair;

        public CoursViewHolder (final View itemView)
        {
            super(itemView);

            titre = ((TextView) itemView.findViewById(R.id.titreCours));
            //explications = ((TextView) itemView.findViewById(R.id.coursExplications));

        }

        public void display(String pair)
        {
            this.currentPair = pair;
            String[] separated = currentPair.split("\\.");
            this.titre.setText(separated[0]);

            //this.explications.setText(pair.second.toString());
        }
    }
}
