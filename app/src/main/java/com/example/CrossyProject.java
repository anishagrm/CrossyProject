package com.example;

import android.app.Application;
import android.content.Context;

public class CrossyProject extends Application {
    private static Context context;
    public void onCreate() {
        super.onCreate();
        CrossyProject.context = getApplicationContext();
    }

    public static Context getContext() {
        return CrossyProject.context;
    }
}
