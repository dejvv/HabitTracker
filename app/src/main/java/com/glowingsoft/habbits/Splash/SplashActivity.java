package com.glowingsoft.habbits.Splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.glowingsoft.habbits.MainActivity;
import com.glowingsoft.habbits.R;

/**
 * Created by david on 09/01/2018.
 */

public class SplashActivity extends Activity implements Quote.OnProgressListener {
    ProgressBar pb; // progress bar
    TextView tv, tv1; // besedilo
    RequestQueue requestQueue; // vrsta za zahteve
    String url; // url za zahtevke
    String author; // avtor citat
    String quote; // citat

    private Quote citat; // qoute of the day

    // zastavica
    boolean hehe;
    boolean displayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // v tem tretnutko še ne poznam avtorja in citata
        this.author = "";
        this.quote = "";

        // inicializacija
        this.citat = null;
        this.pb = (ProgressBar) (findViewById(R.id.progressBarSplash));
        this.tv = (TextView) (findViewById(R.id.textViewSplash));
        this.tv1 = (TextView) (findViewById(R.id.textViewSplash1));
        this.requestQueue = Volley.newRequestQueue(getApplicationContext());
        this.url = "http://quotes.rest/qod.json";
        this.displayed = false;

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
        for (int i = 0; i < table.length && !displayed; i++) {
            if (table[i] == null || table[i].equals("")) {
                hehe = false;
                break;
            }
        }
        if (hehe && !displayed) {
            Log.d("[REST success]tv",quote + ", '" + author + "'" );
            quote = quote.substring(0, quote.length() - 1); // odstranim zadnji znak(piko)
            tv.setText(new String('"' + quote + '"'));
            tv1.setText(new String(author));
            RunAnimation();
            displayed = true;
        }
    }

    @Override
    public void onStopped(String[] strings) {
        citat = null;

        Toast.makeText(getApplicationContext(),
                ("Welcome traveler!"),
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void RunAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.scale);
        a.reset();
        tv.clearAnimation();
        tv.startAnimation(a);
        tv1.clearAnimation();
        tv1.startAnimation(a);
    }
}
