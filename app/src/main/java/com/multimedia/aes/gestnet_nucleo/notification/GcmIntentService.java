package com.multimedia.aes.gestnet_nucleo.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Random;

public class GcmIntentService extends FirebaseMessagingService {
    int notification_id;
    private static NotificationManager mNotificationManager;

    public GcmIntentService() {
    }
    @Override
    public void onMessageReceived(RemoteMessage message){
        Map data = message.getData();
        String c [] = new String[data.size()];
        c[0] = data.get("notification").toString();
        c[1] = data.get("data").toString();
        sendNotification(c);
    }
    private void sendNotification(String [] msg){
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        JSONObject jsonObjectNotification = null;
        JSONObject jsonObjectData = null;
        String titulo = "", mensaje="";
        int metodo = 0,id=0;
        try {
            jsonObjectNotification = new JSONObject(msg[0]);
            jsonObjectData = new JSONObject(msg[1]);
            titulo = jsonObjectNotification.getString("title");
            mensaje = jsonObjectNotification.getString("text");
            metodo = jsonObjectData.getInt("metodo");
            id = jsonObjectData.getInt("ID");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent i = new Intent(this,Index.class);
        i.putExtra("metodo",metodo);
        i.putExtra("id",id);

        Random r = new Random();
        String n = r.nextInt(99999)+""+id;
        notification_id = Integer.parseInt(n);
        i.putExtra("notiId",notification_id);

        PendingIntent contentIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), i, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle(titulo)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setStyle(new NotificationCompat.BigTextStyle())
                        .setAutoCancel(true)
                        .setOngoing(true)
                        .setContentText(mensaje);
        builder.setContentIntent(contentIntent);
        mNotificationManager.notify(notification_id, builder.build());
    }
    public static void cerrarNotificacion(int id){
        mNotificationManager.cancel(id);
    }
}