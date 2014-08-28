package dk.stiandahl.exercise1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainActivity extends Activity {

    String msg = "Android : ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "The onCreate() event");

        Spinner spinner = (Spinner) findViewById(R.id.starwars_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.starwars_chars, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    protected void onStart() { super.onStart();
        Log.d(msg, "The onStart() event");
    }

    @Override
    protected void onResume() { super.onResume();
        Log.d(msg, "The onResume() event");
    }

    @Override
    protected void onPause() { super.onPause();
        Log.d(msg, "The onPause() event");
    }

    @Override
    protected void onStop() { super.onStop();
        Log.d(msg, "The onStop() event");
    }

    @Override
    public void onDestroy() { super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }
}
