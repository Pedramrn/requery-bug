package com.github.pedramrn.requerybug.ui.first;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.bluelinelabs.conductor.Controller;
import com.github.pedramrn.requerybug.App;
import com.github.pedramrn.requerybug.databinding.ControllerFirstBinding;

import javax.inject.Inject;

import io.requery.Persistable;
import io.requery.reactivex.ReactiveEntityStore;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-13
 */

public class ControllerFirst extends Controller {


    @Inject
    PresenterFirst presenter;

    ViewModelFirst viewModelFirst;
    @Inject
    ReactiveEntityStore<Persistable> entityStore;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        App.getMainComponent().inject(this);

        if (viewModelFirst == null) {
            viewModelFirst = new ViewModelFirst(presenter);
        }


        final ControllerFirstBinding binding = ControllerFirstBinding.inflate(inflater, container, false);
        binding.setViewModelUser(viewModelFirst);

        binding.checkBoxShouldBeSame.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                presenter.updateUser("from checkbox up there ^", isChecked).toCompletable().subscribe();
            }
        });

        return binding.getRoot();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
