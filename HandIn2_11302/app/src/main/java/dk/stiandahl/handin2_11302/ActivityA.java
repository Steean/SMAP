package dk.stiandahl.handin2_11302;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
        EditText inputText = (EditText) findViewById(R.id.et_seconds);

        Intent i = new Intent(this, AlarmService.class);
        i.putExtra("seconds", inputText.getText());

        startService(i);
    }
}
