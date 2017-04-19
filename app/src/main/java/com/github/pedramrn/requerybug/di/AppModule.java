package com.github.pedramrn.requerybug.di;

import android.content.Context;

import com.github.pedramrn.requerybug.BuildConfig;
import com.github.pedramrn.requerybug.database.Models;
import com.github.pedramrn.requerybug.database.UserEntity;
import com.github.pedramrn.requerybug.ui.main.di.MainComponent;

import dagger.Module;
import dagger.Provides;
import io.requery.Persistable;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveSupport;
import io.requery.sql.Configuration;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-03-01
 */
@Module(subcomponents = MainComponent.class)
public class AppModule {
    private static final String TAG = AppModule.class.getSimpleName();
    private final Context context;

    public AppModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides
    public Context getContext() {
        return context;
    }

    @Provides
    public ReactiveEntityStore<Persistable> entityStore(Context context) {
        final DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 1);
        if (BuildConfig.DEBUG) {
            // use this in development mode to drop and recreate the tables on every upgrade
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
            source.setLoggingEnabled(true);
        }
        final Configuration configuration = source.getConfiguration();
        final ReactiveEntityStore<Persistable> entityStore =
                ReactiveSupport.toReactiveStore(new EntityDataStore<Persistable>(configuration));

        if (entityStore.select(UserEntity.class).get().firstOrNull() == null) {
            final UserEntity userEntity = new UserEntity();
            userEntity.setBio("You're awesome");
            userEntity.setAwesome(true);
            userEntity.setName("joe");
            entityStore.insert(userEntity).subscribe();
        }
        return entityStore;
    }

}
