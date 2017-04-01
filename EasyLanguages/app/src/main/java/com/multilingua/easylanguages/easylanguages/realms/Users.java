package com.multilingua.easylanguages.easylanguages.realms;

import com.multilingua.easylanguages.easylanguages.element.Alerte;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alexandre on 15/02/2017.
 */

public class Users extends RealmObject {

    private Date date;
    @PrimaryKey
    private int id;
    @Required
    private String utilisateur, mdp;
    private int cours;
    private RealmList<Alerte> alertes;

    public Users(){}

    public Users(String email, String mdp, int cours)
    {
        this.mdp=mdp;
        this.utilisateur=email;
        this.cours = 0;
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id=id;}

    public String getUtilisateur() {return this.utilisateur;}
    public String getMdp() {return this.mdp;}

    public void setUtilisateur(String utilisateur) {this.utilisateur = utilisateur;}
    public void setMdp(String mdp) {this.mdp = mdp;}

    public int getCours() {return this.cours;}
    public void setCours(int cours) {this.cours = cours;}

    public Alerte getAlerte(int i){return alertes.get(i);}
    public RealmList<Alerte> getAlertes(){return alertes;}
    public void addAlerte(Alerte alerte)
    {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        this.alertes.add(alerte);
        realm.commitTransaction();
    }
    public void setAlertes(RealmList<Alerte> rl){this.alertes = rl;}

    public void setDate(Date date){this.date = date;}
    public Date getDate(){return this.date;}
}
