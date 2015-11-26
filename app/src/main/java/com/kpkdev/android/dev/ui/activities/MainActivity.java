package com.kpkdev.android.dev.ui.activities;

import com.kpkdev.android.dev.R;
import com.kpkdev.android.dev.navigation.events.NavigationEvent;
import com.kpkdev.android.dev.utils.Logger;
import com.squareup.otto.Subscribe;

public class MainActivity extends BaseDrawerActivity {

    @Override
    int getLayoutId() {
        return R.layout.activity_main;
    }

    @Subscribe
    public void onNavigationEvent(NavigationEvent event) {
        Logger.d("Navigation called on MainActivity" );
        consumeNavigationEvent(event);
    }
}
