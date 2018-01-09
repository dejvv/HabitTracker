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

public class MainActivity extends Activity implements Quote.OnProgressListener{

    ProgressBar pb; // progress bar
    TextView tv; // besedilo
    RequestQueue requestQueue; // vrsta za zahteve
    String url; // url za zahtevke
    String author; // avtor citat
    String quote; // citat
    Button start;

    private Quote citat; // qoute of the day

    // zastavica
    boolean hehe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // v tem tretnutko še ne poznam avtorja in citata
        this.author = "";
        this.quote = "";

        // inicializacija
        this.citat = null;
        this.pb = (ProgressBar) (findViewById(R.id.progressBar));
        this.tv = (TextView) (findViewById(R.id.textView));
        this.start = (Button) findViewById(R.id.button);
        this.requestQueue = Volley.newRequestQueue(getApplicationContext());
        this.url = "http://quotes.rest/qod.json";

        start.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {
                citat = new Quote();
                citat.setOnProgressListener(MainActivity.this);
                citat.execute(getApplicationContext(), url); // iz tega urlja mi prenesi quote
            }
        });

    }

    @Override
    public void onStarted(){
        pb.setProgress(0);
        start.setEnabled(false);
    }

    @Override
    public void onProgress(double progress, String [] table){
        pb.setProgress((int)progress);
        hehe = true;
        // če še podatki niso prenešeni
        for(int i = 0; i < table.length; i++){
            if(table[i] == null || table[i].equals("")) {
                hehe = false;
                break;
            }
        }
        if(hehe){
            tv.setText(new String(table[0] + ", '" + table[1] + "'"));
        }
    }

    @Override
    public void onStopped(String[] strings) {
        quote = strings[0];
        author = strings[1];
        Toast.makeText(getApplicationContext(),
                ("working done hehehe"),
                Toast.LENGTH_SHORT).show();
        citat = null;
        start.setEnabled(true);
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
