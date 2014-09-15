package dk.stiandahl.handin2_11302;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by stian on 15/09/14.
 */
public class AlarmService extends Service {

    private 

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {



        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }
}
