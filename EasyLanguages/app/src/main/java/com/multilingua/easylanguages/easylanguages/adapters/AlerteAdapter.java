package com.multilingua.easylanguages.easylanguages.adapters;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.multilingua.easylanguages.easylanguages.activites.Alertes;
import com.multilingua.easylanguages.easylanguages.element.Alerte;
import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Alexandre on 13/03/2017.
 */

public class AlerteAdapter extends RecyclerView.Adapter<AlerteAdapter.AlerteViewHolder> {

    private RealmList<Alerte> listeDesAlertes;
    private Context context;
    private Users user;

    public AlerteAdapter(RealmList<Alerte> liste, Context context, Users user){
        this.listeDesAlertes=liste;
        this.context = context;
        this.user = user;
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
        private ImageButton delete;
        private Date date;
        private String nom;

        public AlerteViewHolder(final View itemView)
        {
            super(itemView);
            alerte = (TextView) itemView.findViewById(R.id.titreDeLAlerte);
            dateAlerte = (TextView) itemView.findViewById(R.id.dateDeLAlerte);
            delete = (ImageButton) itemView.findViewById(R.id.deleteOnRecyclerView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(delete.getVisibility() == View.INVISIBLE){
                        delete.setVisibility(delete.VISIBLE);
                        CharSequence texte = "Appui long pour enlever l'icone de suppression";
                        Toast.makeText(context, texte, Toast.LENGTH_SHORT);
                    }
                    else{
                        delete.setVisibility(delete.INVISIBLE);
                    }
                    return false;
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Realm realm = Realm.getDefaultInstance();
                    int position = getAdapterPosition();
                    realm.beginTransaction();
                    user.getAlertes().remove(position);
                    realm.copyToRealmOrUpdate(user);
                    realm.commitTransaction();
                    Intent i = new Intent(context, Alertes.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                    //todo : retirer alarm et finish ?!
                    //PendingIntent pendingIntent = PendingIntent.getBroadcast(context, )
                }
            });
        }


        public void display(Alerte alerte)
        {
            this.date = alerte.getDate();
            String name = alerte.getNom();

            String[] separated = name.split("\\.");

            this.alerte.setText(separated[0]);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date dateAlerte = alerte.getDate();
            String dateStringAlerte = dateFormat.format(dateAlerte);
            this.dateAlerte.setText(dateStringAlerte);
        }
    }
}
