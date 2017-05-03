package com.multilingua.easylanguages.easylanguages.realms;

/**
 * Created by Alexandre on 15/02/2017.
 */

import android.app.Application;

import com.multilingua.easylanguages.easylanguages.element.Alerte;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public final class RealmController{

    private static RealmController instance;
    private Realm realm;

    public RealmController(Application application) {

        Realm.init(application.getApplicationContext());
        this.realm = Realm.getDefaultInstance();
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //find all objects in the Book.class
    public RealmResults<Users> getUsers() {

        return realm.where(Users.class).findAll();
    }

    //query a single item with the given id
    public Users getUserById(String id) {

        return realm.where(Users.class).equalTo("id", id).findFirst();
    }

    public Users getUserByName(String name) {
        return realm.where(Users.class).equalTo("utilisateur", name).findFirst();
    }

    public int getUsersNumber()
    {
        RealmResults<Users> result = getUsers();
        return result.size();
    }

    //query example
    /*public RealmResults<user> queryedBooks() {

        return realm.where(user.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();
    }*/

}