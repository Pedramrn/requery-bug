package com.github.pedramrn.requerybug.ui.cart;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.github.pedramrn.requerybug.R;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-04-17
 */

public class ControllerCart extends Controller {
    private static final String TAG = ControllerCart.class.getSimpleName();

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
        return inflater.inflate(R.layout.controller_cart, container, false);
    }
}
