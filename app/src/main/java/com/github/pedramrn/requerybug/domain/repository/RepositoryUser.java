package com.github.pedramrn.requerybug.domain.repository;

import com.github.pedramrn.requerybug.domain.model.User;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-18
 */

public interface RepositoryUser {

    Observable<User> get(String name);

    Single<Integer> update(String bio, boolean awesome);

    Completable create(User user);


}
