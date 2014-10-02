package dk.stiandahl.handin4_11302;

import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ListActivity {

    ITogService togService;
    boolean mBound = false;
    private ListView lv;
    ArrayAdapter<String> adapter = null;
    private EditText filterText = null;
    ArrayList<String> stationList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(android.R.id.list);

        LocalBroadcastManager.getInstance(this).registerReceiver(listReceiver, new IntentFilter("stations"));

        filterText = (EditText) findViewById(R.id.filterInput);
        filterText.addTextChangedListener(filterTextWatcher);

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stationList));
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Bind to LocalService
        Intent intent = new Intent(this, ITogService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void onClick_searchBtn(View view) {
        if (mBound)
            togService.FetchStations("http://stog.itog.dk/itog/action/list/format/json");
    }

    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            ITogService.ITogBinder binder = (ITogService.ITogBinder) service;
            togService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private BroadcastReceiver listReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            stationList = intent.getStringArrayListExtra("stationList");

            adapter = new ArrayAdapter<String>(
                    MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    stationList );

            lv.setAdapter(adapter);
        }
    };

    private TextWatcher filterTextWatcher = new TextWatcher() {

        public void afterTextChanged(Editable s) {
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            adapter.getFilter().filter(s);
        }

    };

}
