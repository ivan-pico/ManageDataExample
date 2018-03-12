package controller;

import android.app.Application;
import android.content.res.Configuration;

import network.RetrofitController;

/**
 * Created by ipicomar on 12/03/2018.
 */

// declare under application in AndroidManifest
    /*
    <application
            android:name="controller.ManageDataExampleApplication"
            ...
            */

public class ManageDataExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!

        RetrofitController.getInstance();

    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }



}
