package com.ryan.texturecamerapreview.main;

import android.app.Application;

import com.ryan.texturecamerapreview.utils.AppCore;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.getInstance().init(this);
    }
}
