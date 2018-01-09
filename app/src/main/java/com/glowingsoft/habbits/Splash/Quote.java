package com.glowingsoft.habbits.Splash;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by david on 08/01/2018.
 */

public class Quote extends AsyncTask<Object, Double, String []> {
    public interface OnProgressListener{
        void onStarted();
        void onProgress(double progress, String[] table);
        void onStopped(String[] strings);
    }
    private OnProgressListener onProgressListener = null;

    // vrnem tabelo v kateri sta avtor in quote
    String [] tabela = new String[2];
    // requestQueue

    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // nastavim progressBar na 0
        if (onProgressListener != null)
            onProgressListener.onStarted();
    }

    @Override
    protected String [] doInBackground(Object... objects) {
        RequestQueue requestQueue = Volley.newRequestQueue((Context) objects[0]);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, objects[1].toString(), null, jsonObjectListener, errorListener);
        requestQueue.add(request);
        calcSomething();
        return tabela;
    }

    @Override
    protected void onProgressUpdate(Double... values) {
        super.onProgressUpdate(values);
        if (onProgressListener != null)
            onProgressListener.onProgress(values[0], tabela);
    }

    @Override
    protected void onPostExecute(String[] strings) {
        super.onPostExecute(strings);
        if (onProgressListener != null)
            onProgressListener.onStopped(strings);
    }

    // Error listener zahtevan po standardu
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("[REST error]", error.getMessage());
        }
    };

    private Response.Listener<JSONObject> jsonObjectListener = new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            try {
                JSONObject contents = response.getJSONObject("contents");

                // si zapomnim avtorja in quote
                tabela[0] = contents.getJSONArray("quotes").getJSONObject(0).getString("quote");
                tabela[1] = contents.getJSONArray("quotes").getJSONObject(0).getString("author");
            } catch (JSONException e) {
                e.printStackTrace(); // napaka
            }
            Log.d("[REST success],", response.toString());
            Log.d("[REST success],", tabela[0] + " " + tabela[1]);
        }
    };

    public void calcSomething(){
        int limit = 100;
        for(int i = 0; i < limit; i++) {
            double percent = 100.0 * (double) i / (double) limit;
            publishProgress(percent);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
