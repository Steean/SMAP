package dk.stiandahl.handin4_11302;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ITogService extends Service {

    private final IBinder togBinder = new ITogBinder();

    public class ITogBinder extends Binder {
        ITogService getService() {
            return ITogService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return togBinder;
    }

    public void FetchStations(String url) {
        new DoBackgroundTask().execute(url);
    }

    public class DoBackgroundTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... urls) {

            ArrayList<String> listItems = new ArrayList<String>();

            for(String url : urls){
                try {
                    URL uri = new URL(url);
                    HttpURLConnection request = (HttpURLConnection) uri.openConnection();

                    request.connect();

                    int status = request.getResponseCode();

                    Log.d("status", "HTTP Status: " + Integer.toString(status));

                    switch (status) {
                        case 200:
                        case 201:
                            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
                            String line;
                            while ((line = br.readLine()) != null) {
                                JSONArray ja = new JSONArray(line);

                                for (int i = 0; i < ja.length(); i++) {
                                    JSONObject jo = (JSONObject) ja.get(i);
                                    listItems.add(jo.getString("name"));
                                }
                            }

                            br.close();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return listItems;

        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            //Do anything with response..
            Log.d("test", "nu er vi er her");
        }
    }
}
