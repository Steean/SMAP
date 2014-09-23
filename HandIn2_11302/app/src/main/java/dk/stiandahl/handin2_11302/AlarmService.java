package dk.stiandahl.handin2_11302;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.app.PendingIntent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by stian on 15/09/14.
 */

public class AlarmService extends IntentService {

    private PendingIntent pendingAlarmIntent;

    public static final String ACTION_AlarmService = "dk.stiandahl.handin2_11302.AlarmService";

    public AlarmService() {
        super("AlarmService");
    }

    @Override
    public IBinder onBind(Intent intent) {
        //TODO for communication return IBinder implementation
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("TAG", "onStartCommand AlarmService");
        int data = intent.getExtras().getInt("seconds");

        Calendar calendar = Calendar.getInstance();

        Intent receiverIntent = new Intent(this, MyReceiver.class);
        receiverIntent.putExtra("text", intent.getExtras().get("text").toString());
        pendingAlarmIntent = PendingIntent.getBroadcast(this, 0, receiverIntent, 0);

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        manager.set(AlarmManager.RTC, (calendar.getTimeInMillis() + (data * 1000)), pendingAlarmIntent);

        for(int i = data; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            Intent intentCountdown = new Intent();
            //intentCountdown.setAction(ACTION_AlarmService);
            //intentCountdown.addCategory(Intent.CATEGORY_DEFAULT);
            intentCountdown.putExtra("countdown", Integer.toString(i));
            sendBroadcast(intentCountdown);
        }

    }
}
