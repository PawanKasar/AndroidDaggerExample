package com.example.daggerexample.Interfaces;

import com.example.daggerexample.Activities.MainActivity;
import com.example.daggerexample.Modules.ApiModule;
import com.example.daggerexample.Modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity activity);
}
