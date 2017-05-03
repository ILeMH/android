package com.multilingua.easylanguages.easylanguages.element;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Alexandre on 15/03/2017.
 */

public class Alerte extends RealmObject{

    private Date date;
    private String nom;
    private int code;

    public Alerte(){}

    public Alerte(String nom, Date date, int code){
        this.date = date;
        this.nom = nom;
        this.code = code;
    }

    public Date getDate(){return date;}
    public void setDate(Date date){this.date=date;}

    public String getNom(){return nom;}
    public void setNom(String nom){this.nom=nom;}

    public int getCode(){return code;}
    public void setCode(int code){this.code = code;}
}
