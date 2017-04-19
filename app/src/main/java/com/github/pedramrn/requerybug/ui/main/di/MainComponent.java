package com.github.pedramrn.requerybug.ui.main.di;

import com.github.pedramrn.requerybug.ui.ActivityMain;
import com.github.pedramrn.requerybug.ui.main.ControllerMain;
import com.github.pedramrn.requerybug.ui.first.ControllerFirst;
import com.github.pedramrn.requerybug.ui.second.ControllerSecond;

import dagger.Subcomponent;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-03-01
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(ActivityMain activity);

    void inject(ControllerMain homeController);

    void inject(ControllerFirst controllerFirst);

    void inject(ControllerSecond controllerSecond);

    @Subcomponent.Builder
    interface Builder {
        Builder mainModule(MainModule module);

        MainComponent build();

    }

}
