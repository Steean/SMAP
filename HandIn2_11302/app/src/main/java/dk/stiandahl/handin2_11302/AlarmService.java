package dk.stiandahl.handin2_11302;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.app.PendingIntent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by stian on 15/09/14.
 */

public class AlarmService extends Service {

    private PendingIntent pendingAlarmIntent;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG", "onStartCommand AlarmService");
        int data = intent.getExtras().getInt("seconds");

        Calendar calendar = Calendar.getInstance();

        Intent receiverIntent = new Intent(this, MyReceiver.class);
        receiverIntent.putExtra("text", intent.getExtras().get("text").toString());
        pendingAlarmIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0);

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        manager.set(AlarmManager.RTC, (calendar.getTimeInMillis() + (data * 1000)), pendingAlarmIntent);

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }
}
