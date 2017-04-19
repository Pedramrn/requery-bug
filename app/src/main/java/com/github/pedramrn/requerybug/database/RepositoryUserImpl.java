package com.github.pedramrn.requerybug.database;

import android.util.Log;

import com.github.pedramrn.requerybug.domain.model.User;
import com.github.pedramrn.requerybug.domain.repository.RepositoryUser;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;
import io.requery.reactivex.ReactiveResult;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-18
 */

public class RepositoryUserImpl implements RepositoryUser {

    ReactiveEntityStore<Persistable> entityStore;
    private static final String TAG = RepositoryUserImpl.class.getSimpleName();

    @Inject
    public RepositoryUserImpl(ReactiveEntityStore<Persistable> entityStore) {
        this.entityStore = entityStore;
    }

    @Override
    public Observable<User> get(String name) {
        return entityStore.select(UserEntity.class)
                .where(UserEntity.NAME.equal(name))
                .get()
                .observableResult()
                .flatMap(new Function<ReactiveResult<UserEntity>, ObservableSource<UserEntity>>() {
                    @Override
                    public ObservableSource<UserEntity> apply(@NonNull ReactiveResult<UserEntity> userEntities) throws Exception {
                        Log.d(TAG, "apply() called with: userEntities = [" + userEntities + "]");
                        return userEntities.observable();
                    }
                }).map(new Function<UserEntity, User>() {
                    @Override
                    public User apply(@NonNull UserEntity userEntity) throws Exception {
                        Log.d(TAG, "apply() called with: userEntity = [" + userEntity + "]");
                        return User.create(userEntity.getId(), userEntity.getBio(), userEntity.isAwesome(), userEntity.getName());
                    }
                }).distinctUntilChanged();
    }

    @Override
    public Single<Integer> update(String bio, boolean awesome) {
        return entityStore.update(UserEntity.class)
                .set(UserEntity.AWESOME, awesome)
                .set(UserEntity.BIO, bio)
                .where(UserEntity.NAME.equal("joe"))
                .get()
                .single();
    }

    @Override
    public Completable create(User user) {
        return entityStore.insert(UserEntity.newUser(user)).toCompletable();
    }
}
