package emp.fri.si.habittracker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;


/**
 *  GET http://quotes.rest/qod.json
 *  quote of the day
 */

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    // Ko aplikacijo nadaljujem spet prensem quote
//    @Override
//    protected void onResume() {
//        super.onResume();
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, this.url, null, jsonObjectListener, errorListener);
//        requestQueue.add(request);
//    }

//    // Error listener zahtevan po standardu
//    private Response.ErrorListener errorListener = new Response.ErrorListener() {
//        @Override
//        public void onErrorResponse(VolleyError error) {
//            Log.d("[REST error]", error.getMessage());
//        }
//    };
//
//    private Response.Listener<JSONObject> jsonObjectListener = new Response.Listener<JSONObject>() {
//        @Override
//        public void onResponse(JSONObject response) {
//            try {
//                JSONObject contents = response.getJSONObject("contents");
//
//                // si zapomnim avtorja in quote
//                quote = contents.getJSONArray("quotes").getJSONObject(0).getString("quote");
//                author = contents.getJSONArray("quotes").getJSONObject(0).getString("author");
//
//                Log.d("hehehe", author + ": '" + quote + "'");
//                tv.setText(new String(author + ": '" + quote + "'"));
//            } catch (JSONException e) {
//                e.printStackTrace(); // napaka
//            }
//            Log.d("[REST success],", response.toString());
//        }
//    };
}
