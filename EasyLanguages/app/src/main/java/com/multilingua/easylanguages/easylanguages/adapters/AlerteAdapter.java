package com.multilingua.easylanguages.easylanguages.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.multilingua.easylanguages.easylanguages.element.Alerte;
import com.multilingua.easylanguages.easylanguages.R;

import java.util.Date;

import io.realm.RealmList;

/**
 * Created by Alexandre on 13/03/2017.
 */

public class AlerteAdapter extends RecyclerView.Adapter<AlerteAdapter.AlerteViewHolder> {

    private RealmList<Alerte> listeDesAlertes;

    public AlerteAdapter(RealmList<Alerte> liste){
        this.listeDesAlertes=liste;
    }

    @Override
    public AlerteAdapter.AlerteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.alerteunique, parent, false);
        return new AlerteAdapter.AlerteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlerteAdapter.AlerteViewHolder holder, int position) {
        Alerte liste = listeDesAlertes.get(position);
        holder.display(liste);
    }

    @Override
    public int getItemCount() {
        return listeDesAlertes.size();
    }

    public class AlerteViewHolder extends RecyclerView.ViewHolder
    {
        private TextView alerte;
        private TextView dateAlerte;
        private Date date;
        private String nom;

        public AlerteViewHolder(final View itemView)
        {
            super(itemView);
            alerte = (TextView) itemView.findViewById(R.id.titreDeLAlerte);
            dateAlerte = (TextView) itemView.findViewById(R.id.dateDeLAlerte);

        }

        public void display(Alerte alerte)
        {
            this.date = alerte.getDate();
            String name = alerte.getNom();

            String[] separated = name.split("\\.");

            this.alerte.setText(separated[0]);
            this.dateAlerte.setText(alerte.getDate().toString());
        }
    }
}
