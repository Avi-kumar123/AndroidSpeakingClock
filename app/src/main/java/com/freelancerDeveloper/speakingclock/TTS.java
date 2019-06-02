package com.freelancerDeveloper.speakingclock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class TTS extends Service implements TextToSpeech.OnInitListener {

    private TextToSpeech tts;

    private int mHour, mMinute;

    SimpleDateFormat simpleDateFormat;
    String time;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        tts = new TextToSpeech(this, this);
        speakOut();
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            int result = tts.setLanguage(Locale.US);
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }
            speakOut();
        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut() {
        final Calendar c = Calendar.getInstance();

        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);


        simpleDateFormat = new SimpleDateFormat("hh:mm");

        time = simpleDateFormat.format(c.getTime());
        tts.setLanguage(new Locale("hin","IN"));
        tts.setPitch((float) 0.6);
        tts.speak(time, TextToSpeech.QUEUE_FLUSH, null);
    }
}