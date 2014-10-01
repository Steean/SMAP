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
import org.json.JSONException;
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

    private String convertStreamToString(InputStream is) throws IOException {
        if (is != null) {
            StringBuilder sb = new StringBuilder();
            String line = null;

            try {
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(is, "UTF-8"));
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
            } finally {
                is.close();
            }
            return sb.toString();
        } else {
            return "";
        }
    }

    public class DoBackgroundTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            ArrayList<String> listItems = new ArrayList<String>();
            String result = "";

            for(String url : urls){
                try {
                    URL uri = new URL(url);
                    HttpURLConnection request = (HttpURLConnection) uri.openConnection();

                    request.connect();

                    int status = request.getResponseCode();

                    Log.d("status", "HTTP Status: " + Integer.toString(status));

                    result = convertStreamToString(request.getInputStream());
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
            Log.d("test", "nu er vi er her");
        }
    }
}
