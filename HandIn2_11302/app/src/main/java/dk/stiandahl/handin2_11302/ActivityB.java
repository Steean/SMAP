package dk.stiandahl.handin2_11302;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Stian on 18-09-2014.
 */
public class ActivityB extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "onCreate ActivityB");

        setContentView(R.layout.activity_b);

        TextView tv = (TextView) findViewById(R.id.tv_activityB);
        tv.setText(getIntent().getStringExtra("text"));
    }
}
