package com.github.pedramrn.requerybug.database;

import io.requery.Column;
import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.Table;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-10
 */

@Entity
@Table(name = "table_user_data")
public abstract class User {

    @Key
    @Generated
    public abstract int getId();

    public abstract String getBio();

    public abstract boolean isAwesome();


    @Column(unique = true)
    public abstract String getName();

    public static UserEntity newUser(com.github.pedramrn.requerybug.domain.model.User user) {
        final UserEntity userEntity = new UserEntity();
        userEntity.setAwesome(user.isAwesome());
        userEntity.setBio(user.getBio());
        userEntity.setName(user.getName());
        return userEntity;
    }
}
