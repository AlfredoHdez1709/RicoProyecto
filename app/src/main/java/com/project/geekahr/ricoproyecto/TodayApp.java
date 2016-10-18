package com.project.geekahr.ricoproyecto;

import android.app.Application;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by geekahr on 10/15/16.
 */

public class TodayApp extends Application {
    @Override
    public  void onCreate(){
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
}
