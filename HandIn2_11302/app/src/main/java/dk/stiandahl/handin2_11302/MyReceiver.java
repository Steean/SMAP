package dk.stiandahl.handin2_11302;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by Stian on 18-09-2014.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TAG", "onReceive MyReceiver");

        Intent activityB = new Intent(context, ActivityB.class);
        activityB.putExtra("text", intent.getExtras().get("text").toString());
        activityB.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(activityB);

        ActivityA.progg_bar.setProgress(0);
    }
}
