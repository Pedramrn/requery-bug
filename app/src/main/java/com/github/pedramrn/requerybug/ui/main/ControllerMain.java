package com.github.pedramrn.requerybug.ui.main;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.support.RouterPagerAdapter;
import com.github.pedramrn.requerybug.App;
import com.github.pedramrn.requerybug.R;
import com.github.pedramrn.requerybug.databinding.ControllerHomeBinding;
import com.github.pedramrn.requerybug.ui.cart.ControllerCart;
import com.github.pedramrn.requerybug.ui.first.ControllerFirst;
import com.github.pedramrn.requerybug.ui.second.ControllerSecond;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

/**
 * @author : Pedramrn@gmail.com
 *         Created on: 2017-02-13
 */

public class ControllerMain extends Controller implements BottomNavigation.OnMenuItemSelectionListener {

    private static final String TAG = ControllerMain.class.getSimpleName();

    private ControllerHomeBinding binding;
    private final RouterPagerAdapter routerPagerAdapter;

    public ControllerMain() {
        routerPagerAdapter = new RouterPagerAdapter(this) {

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public void configureRouter(Router router, int position) {
                if (!router.hasRootController()) {
                    switch (position) {
                        case 0:
                            router.setRoot(RouterTransaction.with(new ControllerFirst()));
                            break;
                        case 1:
                            router.setRoot(RouterTransaction.with(new ControllerSecond()));
                            break;
                        case 2:
                            router.setRoot(RouterTransaction.with(new ControllerCart()));
                            break;
                    }
                }
            }
        };
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        App.getMainComponent().inject(this);
        binding = ControllerHomeBinding.inflate(layoutInflater, viewGroup, false);
        binding.viewPager.setAdapter(routerPagerAdapter);
        binding.navigation.setOnMenuItemClickListener(this);
        return binding.getRoot();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.disposeMainComponent();
    }

    @Override
    public void onMenuItemSelect(@IdRes int itemId, int position, boolean fromUser) {
        if (fromUser) {
            binding.viewPager.setCurrentItem(position, false);
            switch (itemId) {
                case R.id.navigation_first:
                    break;
                case R.id.navigation_second:
                    break;
                case R.id.navigation_notifications:
                    break;
            }
        }

    }

    @Override
    public void onMenuItemReselect(@IdRes int itemId, int position, boolean fromUser) {
    }
}
