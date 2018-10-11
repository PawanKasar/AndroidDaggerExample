package com.example.daggerexample.MyApplication;

import android.app.Application;

import com.example.daggerexample.Interfaces.ApiComponent;
import com.example.daggerexample.Interfaces.DaggerApiComponent;
import com.example.daggerexample.Modules.ApiModule;
import com.example.daggerexample.Modules.AppModule;

public class MyApplicationClass extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule("https://simplifiedcoding.net/demos/"))
                .build();
    }

    public ApiComponent getNetComponent() {
        return mApiComponent;
    }
}
