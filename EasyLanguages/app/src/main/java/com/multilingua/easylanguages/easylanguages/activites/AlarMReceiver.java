package com.multilingua.easylanguages.easylanguages.activites;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

import com.multilingua.easylanguages.easylanguages.R;

/**
 * Created by Alexandre on 24/04/2017.
 */

public class AlarMReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

        builder.setVibrate(new long[] {500,500});

        int day = intent.getIntExtra("day", 0);
        int minutes = intent.getIntExtra("minutes", 0);
        int month = intent.getIntExtra("month", 0);
        int code = day + minutes + month;

        builder.setSmallIcon(R.drawable.icone);
        //Que du blanc, jouer avec le transparant.
        builder.setContentTitle("EasyLanguages "+ String.valueOf(code));
        builder.setContentText("Vous avez un rendez-vous : " + String.valueOf(day) + " " + String.valueOf(minutes));


        final Intent resultIntent = new Intent(context, MainActivity.class);
        final PendingIntent resultPendingIntent = PendingIntent.getActivity(context, code, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setContentIntent(resultPendingIntent);

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(code, builder.build());
    }
}
