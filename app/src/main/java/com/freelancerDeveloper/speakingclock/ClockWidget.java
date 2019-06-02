package com.freelancerDeveloper.speakingclock;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.widget.AnalogClock;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class ClockWidget extends AppWidgetProvider implements TextToSpeech.OnInitListener {

   // TextToSpeech Talktome;
    AnalogClock Clock2;
    TextView ReadText;

    private int mHour, mMinute;

    SimpleDateFormat simpleDateFormat;

    private static final String RED_CLICKED = "RED_CLICKED";
    String time;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.clock_app_widget);


        ComponentName watchWidget = new ComponentName(context, ClockWidget.class);

        remoteViews.setOnClickPendingIntent(
                R.id.AnalogClock,
                getPendingSelfIntent(context, RED_CLICKED)
        );


        appWidgetManager.updateAppWidget(watchWidget, remoteViews);

    }

    protected PendingIntent getPendingSelfIntent(Context context, String action) {
        Intent intent = new Intent(context, getClass());
        intent.setAction(action);

    //    Toast.makeText(context, "Clicked: "+action, Toast.LENGTH_SHORT).show();
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }



        public void onReceive(Context context, Intent intent)
    {
        final Calendar c = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("hh:mm");

        time = simpleDateFormat.format(c.getTime());

        /*String action = intent.getAction();
        if (AppWidgetManager.ACTION_APPWIDGET_UPDATE.equals(action))
        {

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clock_app_widget);

            Intent AlarmClockIntent = new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_LAUNCHER).setComponent(new ComponentName("com.android.alarmclock", "com.android.alarmclock.AlarmClock"));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, AlarmClockIntent, 0);
            views.setOnClickPendingIntent(R.id.Widget, pendingIntent);

            AppWidgetManager.getInstance(context).updateAppWidget(intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS), views);
          //  Clock2 = (AnalogClock)findViewById(R.id.AnalogClock);
          //  Clock2.setOnClickListener(MyAnalogClockOnClickListener);


            views.setOnClickPendingIntent(R.id.AnalogClock,
                    getPendingSelfIntent(context, MyAnalogClockOnClickListener));
        }*/

        super.onReceive(context, intent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.clock_app_widget);
        ComponentName watchWidget = new ComponentName(context, ClockWidget.class);

        // Display which View is clicked

    //    Toast.makeText(context,"onReceive: "+intent.getAction(),Toast.LENGTH_LONG).show();


        if (RED_CLICKED.equals(intent.getAction())) {

            context.startService(new Intent(context, TTS.class));
            // If the Red TextView clicked, then do that
      //      final Calendar c = Calendar.getInstance();
         //   TextToSpeech   Talktome = new TextToSpeech(context, this);

            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);


        //    simpleDateFormat = new SimpleDateFormat("hh:mm");

       //     time = simpleDateFormat.format(c.getTime());


            String myTime= "The Time is  is "
                    + String.valueOf(mHour)
                    + " Hour "
                    +String.valueOf(mMinute)
                    + " Minute";

         //   ReadText.setText(myTime);
         //   Talktome.setLanguage(new Locale("hin","IN"));
         //   Talktome.setPitch((float) 0.6);
         //   Talktome.speak( time, TextToSpeech.QUEUE_FLUSH, null);
        }
        appWidgetManager.updateAppWidget(watchWidget, remoteViews);
    }
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.clock_app_widget);

    //    Toast.makeText(context,"updateAppWidget",Toast.LENGTH_LONG).show();
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onInit(int status) {

    }


}




