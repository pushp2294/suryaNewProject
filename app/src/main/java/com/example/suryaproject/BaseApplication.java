package com.example.suryaproject;


import android.app.Application;

public class BaseApplication extends Application {

    private static Application context;

    public void onCreate() {
        super.onCreate();
        BaseApplication.context = this;
    }

    public static Application getAppContext() {
        return BaseApplication.context;
    }
}

