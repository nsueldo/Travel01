package com.example.uriel.login1;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.app.NotificationManager;


public class BackgroudService extends Service{
    private static final String TAG = "MyService";

    @Override
    public IBinder onBind(Intent i) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        new Thread(new Runnable() {

            @Override
            public void run() {
                Log.d(TAG, "FirstService started");
                // El servicio se finaliza a sí mismo cuando finaliza su
                // trabajo..
                try {
                    // Simulamos trabajo de 5 segundos.
                    Thread.sleep(5000);

                    // Instanciamos e inicializamos nuestro manager.
                    NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    NotificationCompat.Builder constructor = new NotificationCompat.Builder(
                            getBaseContext())
                            .setSmallIcon(android.R.drawable.ic_dialog_info)
                            .setContentTitle("Viajes")
                            .setContentText("Se detectaron viajes disponibles")
                            .setWhen(System.currentTimeMillis());

                    nManager.notify(12345, constructor.build());

                    Log.d(TAG, "sleep finished");
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }



            }
        }).start();

        this.stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }


}
