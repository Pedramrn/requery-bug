package com.github.pedramrn.requerybug;

import android.app.Application;

import com.github.pedramrn.requerybug.di.AppComponent;
import com.github.pedramrn.requerybug.di.AppModule;
import com.github.pedramrn.requerybug.di.DaggerAppComponent;
import com.github.pedramrn.requerybug.ui.main.di.MainComponent;
import com.github.pedramrn.requerybug.ui.main.di.MainModule;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-03-02
 */

public class App extends Application {

    private static AppComponent appComponent;
    private static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static MainComponent getMainComponent() {
        if (mainComponent == null) {
            mainComponent = appComponent.plus().mainModule(new MainModule()).build();
        }
        return mainComponent;
    }

    public static void disposeMainComponent() {
        mainComponent = null;
    }
}
