package com.example.zemoso.miwok;

import android.app.Application;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by zemoso on 6/3/18.
 */

public class SampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                                            .deleteRealmIfMigrationNeeded().build();


        Realm.setDefaultConfiguration(configuration);
        Log.v("STARTED REALM", String.valueOf(Realm.getDefaultInstance().isEmpty()));
    }
}
