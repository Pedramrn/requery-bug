package com.github.pedramrn.requerybug.ui.second;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.bluelinelabs.conductor.Controller;
import com.github.pedramrn.requerybug.App;
import com.github.pedramrn.requerybug.R;

import javax.inject.Inject;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-17
 */

public class ControllerSecond extends Controller {
    private static final String TAG = ControllerSecond.class.getSimpleName();


    @Inject
    PresenterSecond presenter;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        App.getMainComponent().inject(this);
        final View view = inflater.inflate(R.layout.controller_second, container, false);

        final CheckBox checkBoxThird = (CheckBox) view.findViewById(R.id.checkBox_3);
        checkBoxThird.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                presenter.updateUser("from 3rd page.", isChecked).toCompletable().subscribe();
            }
        });

        // TODO: 2017-04-19 if uncommented it will sends the updates
        /*presenter.getUser("joe").subscribe(new Consumer<User>() {
            @Override
            public void accept(@io.reactivex.annotations.NonNull User user) throws Exception {
                Log.d(TAG, "accept() called with: user = [" + user + "]");
            }
        });*/


        return view;
    }
}
