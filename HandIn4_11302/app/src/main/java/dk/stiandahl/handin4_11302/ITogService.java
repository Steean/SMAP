package dk.stiandahl.handin4_11302;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public class DoBackgroundTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... urls) {

            ArrayList<String> listItems = new ArrayList<String>();
            String result = "";

            for(String url : urls){
                try {
                    URL uri = new URL(url);
                    HttpURLConnection request = (HttpURLConnection) uri.openConnection();

                    request.connect();

                    if(request.getResponseCode()==201 || request.getResponseCode()==200)
                    {
                        InputStream response = request.getInputStream();
                        result = convertStreamToString(response);

                        JSONArray outerArray = new JSONArray(result);

                        for(int i = 0; i < outerArray.length(); i++) {
                            JSONObject station = (JSONObject) outerArray.get(i);
                            listItems.add(station.getString("name"));
                        }
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
            Intent intent = new Intent("stations");
            intent.putStringArrayListExtra("stationList",result);
            sendLocationBroadcast(intent);
        }
    }

    private void sendLocationBroadcast(Intent intent){

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
