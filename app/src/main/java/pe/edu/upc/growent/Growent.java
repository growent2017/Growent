package pe.edu.upc.growent;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class Growent extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
