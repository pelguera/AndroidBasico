package pe.cibertec.capitulo9;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MiService extends Service {

    public static final String ACTION_ACTUALIZAR = "pe.cibertec.capitulo9.actions.ACTUALIZAR";

    private int contador = 0;
    private Timer timer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        final LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i = new Intent(ACTION_ACTUALIZAR);
                i.putExtra("contador", ++contador);
                broadcastManager.sendBroadcast(i);
                Log.d("MiService", "contador: " + contador);
            }
        }, 1000, 1000);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
