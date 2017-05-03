package com.multilingua.easylanguages.easylanguages.activites;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.multilingua.easylanguages.easylanguages.element.Alerte;
import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by Alexandre on 18/03/2017.
 */

public class DateFragment extends GlobalForMenu {

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final RealmController rc = new RealmController(getApplication());
        Realm realm = Realm.getDefaultInstance();
        Users client = rc.getUserByName(GlobalForMenu.utilisateur);

        setContentView(R.layout.date_fragment);

        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);
        final Spinner spinner = (Spinner) findViewById(R.id.coursDisponibles);

        String [] listeDeCours = getCours("cours");
        final String [] listeDeCoursDisponible = tableauSpinner(rc.getUserByName(GlobalForMenu.utilisateur).getCours(), listeDeCours);

        int cours = client.getCours();

        //Définir le spinner à partir de ce tableau.
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listeDeCoursDisponible);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

        Button ajouter = (Button) findViewById(R.id.ajouterDateAlerte);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //long date = datePicker.getMaxDate();
                int heure = 0;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    heure = timePicker.getHour();
                    int minutes = timePicker.getMinute();

                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth();
                    int year = datePicker.getYear();
                    Calendar calendar = Calendar.getInstance();
                    calendar.set(year, month, day, heure, minutes, 00);
                    int code = day + minutes + month;

                    String nomCours = spinner.getSelectedItem().toString();

                    final RealmController rc = new RealmController(getApplication());
                    Realm realm = rc.getRealm();
                    Users client = rc.getUserByName(GlobalForMenu.utilisateur);
                    Alerte alerte = new Alerte(nomCours, calendar.getTime(), code);
                    client.addAlerte(alerte);
                    realm.beginTransaction();
                    realm.copyToRealm(client);
                    realm.commitTransaction();

                    long startTime = calendar.getTimeInMillis();

                    final Intent intent = new Intent(getApplicationContext(), AlarMReceiver.class);
                    intent.putExtra("jour", day);
                    intent.putExtra("month", month);
                    intent.putExtra("minutes", minutes);

                    final PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    // code aussi dans le pending Intent. alarmManager.cancel avec le même pendingIntent.
                    final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
                    } else {
                        alarmManager.set(AlarmManager.RTC_WAKEUP, startTime, pendingIntent);
                    }

                /* class uuid à regarder : id unique ==> chaine de car.
                 * randomId.toString.Hashcode
                 *
                 */
                    Intent i = new Intent(getApplicationContext(), Alertes.class);
                    i.putExtra("heure", heure);
                    //i.putExtra("date", date);
                    setResult(Activity.RESULT_OK, i);
                    finish();
                }
            }
        });
    }
}
