package com.github.pedramrn.requerybug.ui.second;

import com.github.pedramrn.requerybug.domain.model.User;
import com.github.pedramrn.requerybug.domain.repository.RepositoryUser;
import com.github.pedramrn.requerybug.database.RepositoryUserImpl;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-17
 */

public class PresenterSecond {

    private final RepositoryUser repositoryUser;

    @Inject
    public PresenterSecond(RepositoryUserImpl repositoryUser) {
        this.repositoryUser = repositoryUser;
    }


    public Observable<User> getUser(String name) {
        return repositoryUser
                .get(name)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    public Completable createUser(User user) {
        return repositoryUser
                .create(user)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }

    public Single<Integer> updateUser(String bio, boolean awesome) {
        return repositoryUser
                .update(bio, awesome)
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread())
                ;
    }
}
