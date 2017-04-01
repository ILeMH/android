package com.multilingua.easylanguages.easylanguages.activites;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.multilingua.easylanguages.easylanguages.element.Alerte;
import com.multilingua.easylanguages.easylanguages.R;
import com.multilingua.easylanguages.easylanguages.realms.RealmController;
import com.multilingua.easylanguages.easylanguages.realms.Users;

import java.util.Calendar;

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
                long date = datePicker.getMaxDate();
                int heureComplete = timePicker.getBaseline();
                int heure = timePicker.getHour();
                int minutes = timePicker.getMinute();
                String heureString = String.valueOf(heureComplete);
                String dateString = String.valueOf(date);
                Log.d("heure", heureString);
                Log.d("date", dateString);

                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year+1900, month, day, heure, minutes);
                String nomCours = spinner.getSelectedItem().toString();

                final RealmController rc = new RealmController(getApplication());
                Realm realm = rc.getRealm();
                Users client = rc.getUserByName(GlobalForMenu.utilisateur);
                Alerte alerte = new Alerte(nomCours, calendar.getTime());
                client.addAlerte(alerte);
                realm.beginTransaction();
                realm.copyToRealm(client);
                realm.commitTransaction();

                Intent i = new Intent(getApplicationContext(), Alertes.class);
                i.putExtra("heure", heure);
                i.putExtra("date", date);
                setResult(Activity.RESULT_OK, i);
                startAlarm(year, month, day, heure, minutes, 00);
                finish();
            }
        });
    }

    private void startAlarm(int year, int month, int date, int hour, int minute, int second) {

        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        Calendar calendar =  Calendar.getInstance();
        calendar.set(year, month, date, hour, minute, second);
        long when = calendar.getTimeInMillis();         // notification time
        Intent intent = new Intent(this, ReminderService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
        alarmManager.set(AlarmManager.RTC, when, pendingIntent);
    }
}
