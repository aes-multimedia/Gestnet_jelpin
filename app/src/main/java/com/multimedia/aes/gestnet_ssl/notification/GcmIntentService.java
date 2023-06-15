package com.multimedia.aes.gestnet_ssl.notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.nucleo.Index;


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


    private void sendNotification(String [] msg) {
        try {
        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        JSONObject jsonObjectNotification = null;
        JSONObject jsonObjectData = null;
        String titulo = "", mensaje = "";
        int metodo = 0, id = 0;

        jsonObjectNotification = new JSONObject(msg[0]);
        jsonObjectData = new JSONObject(msg[1]);
        titulo = jsonObjectNotification.getString("title");
        mensaje = jsonObjectNotification.getString("text");
        metodo = jsonObjectData.getInt("metodo");
        id = jsonObjectData.getInt("ID");

        Intent i = new Intent(this, Index.class);
        i.putExtra("metodo", metodo);
        i.putExtra("id", id);
        Random r = new Random();
        String n = r.nextInt(9999) + "" + id;
        if(n.length() > 9){
            notification_id = Integer.parseInt(n.substring(0,9));
        }else{
            notification_id = Integer.parseInt(n);
        }

        i.putExtra("notiId", id);

        PendingIntent contentIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), i, PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle(titulo)
                        .setSmallIcon(R.drawable.ic_almacen)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_almacen))
                        .setStyle(new NotificationCompat.BigTextStyle())
                        .setAutoCancel(true)
                        .setOngoing(false)
                        .setTicker("hola")
                        .setContentText(mensaje)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_HIGH);
        builder.setContentIntent(contentIntent);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = "8";
                NotificationChannel channel = new NotificationChannel(channelId,
                        "Channel aplicacion",
                        NotificationManager.IMPORTANCE_DEFAULT);
                mNotificationManager.createNotificationChannel(channel);
                builder.setChannelId(channelId);
            }
        mNotificationManager.notify(notification_id, builder.build());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void cerrarNotificacion(int id){
        mNotificationManager.cancel(id);
    }
}