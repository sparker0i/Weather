package com.a5corp.weather;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import com.a5corp.weather.model.Log;

import com.a5corp.weather.activity.FirstLaunch;
import com.a5corp.weather.activity.WeatherActivity;
import com.a5corp.weather.preferences.Preferences;
import com.a5corp.weather.preferences.Prefs;

public class GlobalActivity extends AppCompatActivity {

    public static Preferences cp;
    public static Prefs prefs;
    public static int i = 0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global);
        Log.i("Loaded" , "Global");
    }

    @Override
    protected void onResume() {
        cp = new Preferences(this);
        prefs = new Prefs(this);
        super.onResume();

        if (!cp.getPrefs().getBoolean("first" , true)) {
            prefs.setLaunched();
            prefs.setCity(cp.getCity());
        }

        super.onResume();
        if (prefs.getLaunched()) {
            intent = new Intent(GlobalActivity.this, WeatherActivity.class);
        }
        else {
            intent = new Intent(GlobalActivity.this, FirstLaunch.class);
        }
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        },1000);
    }
}
