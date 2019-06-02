package com.freelancerDeveloper.speakingclock;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AnalogClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    TextToSpeech Talktome;
    AnalogClock Clock2;
    TextView ReadText;

    private int mHour, mMinute;

    SimpleDateFormat simpleDateFormat;
    String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Talktome = new TextToSpeech(this, this);

        Clock2 = (AnalogClock)findViewById(R.id.AnalogClock);
        Clock2.setOnClickListener(MyAnalogClockOnClickListener);
        ReadText = (TextView)findViewById(R.id.Text2read);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Talktome.shutdown();
    }

    private AnalogClock.OnClickListener MyAnalogClockOnClickListener
            = new AnalogClock.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            final Calendar c = Calendar.getInstance();

            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);


            simpleDateFormat = new SimpleDateFormat("hh:mm");

            time = simpleDateFormat.format(c.getTime());


            String myTime= "The Time is  is "
                    + String.valueOf(mHour)
                    + " Hour "
                    +String.valueOf(mMinute)
                    + " Minute";

            ReadText.setText(myTime);
            Talktome.setLanguage(new Locale("hin","IN"));
            Talktome.setPitch((float) 0.6);
            Talktome.speak( time, TextToSpeech.QUEUE_FLUSH, null);
        }
    };
}
