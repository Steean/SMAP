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
    }

    public void btn_alarm_onClick(View view) {
        Log.d("TAG","Knappen er trykket i bund - hans");
        EditText inputText = (EditText) findViewById(R.id.et_seconds);

        Intent i = new Intent(this, AlarmService.class);
        i.putExtra("seconds", Integer.parseInt(inputText.getText().toString()));

        startService(i);
    }
}
