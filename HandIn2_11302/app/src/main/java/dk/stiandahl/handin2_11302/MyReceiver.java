package dk.stiandahl.handin2_11302;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Stian on 18-09-2014.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent activityB = new Intent(context, ActivityB.class);
        context.startActivity(activityB);
    }
}
