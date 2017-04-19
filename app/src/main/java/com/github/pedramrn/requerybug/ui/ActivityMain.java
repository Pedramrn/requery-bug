package com.github.pedramrn.requerybug.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bluelinelabs.conductor.Conductor;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.github.pedramrn.requerybug.App;
import com.github.pedramrn.requerybug.R;
import com.github.pedramrn.requerybug.databinding.ActivityMainBinding;
import com.github.pedramrn.requerybug.ui.main.ControllerMain;

public class ActivityMain extends AppCompatActivity {
    private static final String TAG = ActivityMain.class.getSimpleName();

    private Router router;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityMainBinding view = DataBindingUtil.setContentView(this, R.layout.activity_main);
        router = Conductor.attachRouter(this, view.controllerContainer, savedInstanceState);
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(new ControllerMain()));
        }
    }

    @Override
    public void onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onDestroy() {
        if (isFinishing()) {
            App.disposeMainComponent();
        }
        super.onDestroy();
    }
}
