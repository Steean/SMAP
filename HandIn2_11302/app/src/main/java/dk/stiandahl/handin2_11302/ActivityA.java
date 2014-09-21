package dk.stiandahl.handin2_11302;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class ActivityA extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Log.d("TAG","onCreate ActivityA");
    }

    public void btn_alarm_onClick(View view) {
        Log.d("TAG","btn_alarm_onClick ActivityA");
        EditText inputSeconds = (EditText) findViewById(R.id.et_seconds);
        EditText inputText = (EditText) findViewById(R.id.et_text);

        Intent i = new Intent(this, AlarmService.class);
        i.putExtra("seconds", Integer.parseInt(inputSeconds.getText().toString()));
        i.putExtra("text", inputText.getText());

        startService(i);
    }
}
