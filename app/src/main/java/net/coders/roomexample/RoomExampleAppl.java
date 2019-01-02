package net.coders.roomexample;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class RoomExampleAppl extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public RoomExampleAppl() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }

}
