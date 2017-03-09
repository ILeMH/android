package com.multilingua.easylanguages.easylanguages;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by Alexandre on 15/02/2017.
 */

public class Users extends RealmObject {

    @PrimaryKey
    private int id;
    @Required
    private String utilisateur, mdp;
    private int cours;

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
}
