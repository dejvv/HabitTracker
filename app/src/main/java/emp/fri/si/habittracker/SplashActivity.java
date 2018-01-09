package emp.fri.si.habittracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by david on 09/01/2018.
 */

public class SplashActivity extends Activity implements Quote.OnProgressListener {
    ProgressBar pb; // progress bar
    TextView tv; // besedilo
    RequestQueue requestQueue; // vrsta za zahteve
    String url; // url za zahtevke
    String author; // avtor citat
    String quote; // citat

    private Quote citat; // qoute of the day

    // zastavica
    boolean hehe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // v tem tretnutko še ne poznam avtorja in citata
        this.author = "";
        this.quote = "";

        // inicializacija
        this.citat = null;
        this.pb = (ProgressBar) (findViewById(R.id.progressBar));
        this.tv = (TextView) (findViewById(R.id.textView));
        this.requestQueue = Volley.newRequestQueue(getApplicationContext());
        this.url = "http://quotes.rest/qod.json";

        citat = new Quote();
        citat.setOnProgressListener(SplashActivity.this);
        citat.execute(getApplicationContext(), url); // iz tega urlja mi prenesi quote
    }

    @Override
    public void onStarted() {
        pb.setProgress(0);
    }

    @Override
    public void onProgress(double progress, String[] table) {
        pb.setProgress((int) progress);
        quote = table[0];
        author = table[1];
        hehe = true;
        // če še podatki niso prenešeni
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null || table[i].equals("")) {
                hehe = false;
                break;
            }
        }
        if (hehe) {
            tv.setText(new String(quote + ", '" + author + "'"));
        }
    }

    @Override
    public void onStopped(String[] strings) {
        citat = null;

        Toast.makeText(getApplicationContext(),
                ("working done hehehe"),
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
