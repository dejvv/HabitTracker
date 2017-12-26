package emp.fri.si.habittracker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    TextView tv; // besedilo
    RequestQueue requestQueue; // vrsta za zahteve
    String url; // url za zahtevke

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         *  GET http://quotes.rest/qod.json
         *  quote of the day
         */

        // inicializacija
        tv = (TextView)(findViewById(R.id.textView));
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        url = "http://quotes.rest/qod.json";
    }

    // Ko aplikacijo nadaljujem spet prensem quote
    @Override
    protected void onResume(){
        super.onResume();
        String url = "http://quotes.rest/qod.json";
        JsonArrayRequest request = new JsonArrayRequest(url, jsonArrayListener, errorListener);
        requestQueue.add(request);
    }

    // Error listener zahtevan po standardu
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.d("REST error,", error.getMessage());
        }
    };

    // Vsebina za zahtevek
    private Response.Listener<JSONArray> jsonArrayListener = new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            ArrayList<String> qod = new ArrayList<>();
            for(int i = 0; i < response.length(); i++) {

            }
        }
    };
}
