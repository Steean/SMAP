package dk.stiandahl.handin2_11302;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


public class ActivityA extends Activity {

    public static ProgressBar progg_bar;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("TAG", "onReceive ActivityA Broadcastreceiver");

            progg_bar = (ProgressBar) findViewById(R.id.progg_bar);
            progg_bar.incrementProgressBy(1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Log.d("TAG","onCreate ActivityA");

        IntentFilter intentFilter = new IntentFilter(AlarmService.ACTION_AlarmService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, intentFilter);
    }

    public void btn_alarm_onClick(View view) {
        Log.d("TAG","btn_alarm_onClick ActivityA");
        EditText inputSeconds = (EditText) findViewById(R.id.et_seconds);
        EditText inputText = (EditText) findViewById(R.id.et_text);

        Intent i = new Intent(this, AlarmService.class);
        i.putExtra("seconds", Integer.parseInt(inputSeconds.getText().toString()));
        i.putExtra("text", inputText.getText());

        progg_bar = (ProgressBar) findViewById(R.id.progg_bar);
        progg_bar.setMax(Integer.parseInt(inputSeconds.getText().toString()));
        progg_bar.setProgress(0);

        startService(i);
    }
}