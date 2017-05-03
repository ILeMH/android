package com.multilingua.easylanguages.easylanguages.activites;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.multilingua.easylanguages.easylanguages.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Alexandre on 29/03/2017.
 */

public class ReminderService extends BroadcastReceiver{

    private Calendar cal;
    protected int min;
    private int hou;
    private int jou;
    private int moi;
    private int ann;
    private String title;
    private String corp;

    public ReminderService(){}

    public ReminderService(Calendar cal, String title, String corp){
        this.cal = cal;
        this.min = cal.get(Calendar.MINUTE);
        this.hou = cal.get(Calendar.HOUR);
        this.jou = cal.get(Calendar.DAY_OF_MONTH);
        this.moi = cal.get(Calendar.MONTH);
        this.ann = cal.get(Calendar.YEAR);
        this.title = title;
        this.corp = corp;
    }

    public Calendar getCalendar(){return this.cal;}
    public void setCalendar(Calendar cal){this.cal = cal;}

    @Override
    public void onReceive(Context context, Intent intent) {
        try{

            Date laDate = new Date();
            Calendar c = Calendar.getInstance();
            c.setTime(laDate);
            int minutes = c.get(Calendar.MINUTE);
            int hour = c.get(Calendar.HOUR);
            int jour = c.get(Calendar.DAY_OF_MONTH);
            int mois = c.get(Calendar.MONTH)+1;
            int annee = c.get(Calendar.YEAR);

            if(min == minutes && hou == hour && jou == jour && moi == mois && ann == annee){
                Intent it = new Intent(context, MainActivity.class);
                createNotification(context, intent, title, corp);
            }
        }catch (Exception e){
            Log.e("ERREUR", e.toString());
        }
    }

    public void createNotification(Context context, Intent intent, String title, String corp){
        NotificationManager notif = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(title);
        builder.setContentTitle(title);
        builder.setContentText(corp);
        builder.setSmallIcon(R.drawable.icone);
        builder.setContentIntent(pendingIntent);
        Notification notification = builder.build();

        notification.vibrate = new long[]{150, 300, 150, 300};
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notif.notify(R.drawable.icone, notification);
        /* ID unique de notification. Permet de la retrouver.
         *
         */

        try{
            Uri som = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone toque = RingtoneManager.getRingtone(context, som);
            toque.play();
        }
        catch(Exception e){}
    }
}