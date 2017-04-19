package com.github.pedramrn.requerybug.ui.first;

import android.databinding.ObservableField;
import android.util.Log;

import com.github.pedramrn.requerybug.domain.model.User;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-17
 */

public class ViewModelFirst {
    private static final String TAG = ViewModelFirst.class.getSimpleName();
    public final ObservableField<String> bio = new ObservableField<>();
    public final ObservableField<Boolean> isAwesome = new ObservableField<>();

    public ViewModelFirst(final PresenterFirst presenter) {
        final Observable<User> observable = presenter.getUser("joe").share();
        observable.subscribe(new Consumer<User>() {
            @Override
            public void accept(@NonNull User user) throws Exception {
                Log.d(TAG, "accept() called with: user = [" + user + "]");
                bio.set(user.getBio());
                isAwesome.set(user.isAwesome());
                bio.notifyChange();
                isAwesome.notifyChange();
            }
        });
    }

    public ObservableField<Boolean> getIsAwesome() {
        return isAwesome;
    }
}
