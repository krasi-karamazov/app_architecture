package com.kpkdev.android.dev.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kpkdev.android.dev.R;
import com.kpkdev.android.dev.navigation.NavigationDrawerController;
import com.kpkdev.android.dev.navigation.events.NavigationEvent;
import com.kpkdev.android.dev.utils.BusProvider;
import com.kpkdev.android.dev.utils.Logger;

/**
 * Created by krasimir.karamazov on 7/7/2015.
 */
public abstract class BaseDrawerActivity extends AppCompatActivity {
    NavigationDrawerController mDrawerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mDrawerController = NavigationDrawerController.getInstance();
        mDrawerController.init(this);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerController.getDrawerToggle().syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerController.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    abstract int getLayoutId();

    @Override
    public void onBackPressed() {
        if(mDrawerController.isDrawerOpened()){
            mDrawerController.toggleDrawer();
        }else{
            int backStackCount = getSupportFragmentManager().getBackStackEntryCount();
            if(backStackCount <= 1) {
                finish();
            }else{
                super.onBackPressed();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        BusProvider.getInstance().unregister(this);
    }

    protected final void consumeNavigationEvent(NavigationEvent event) {
        Logger.d("Navigation called on BaseDrawerActivity");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (event.getShouldAnimateTransaction()) {
            transaction.setCustomAnimations(R.anim.left_in, R.anim.left_out, R.anim.left_in, R.anim.left_out);
        }

        if (event.getShouldPopBackStack()) {
            popBackStack();
        }

        if(event.shouldChangeDrawerToBackArrow()) {
            //changeDrawerIndicatorToBackArrow();
        }
        int containerId;
        if(event.getContainerId() != 0) {
            containerId = event.getContainerId();
        }else{
            containerId = R.id.fl_fragment_container;
            transaction.addToBackStack(event.getFragment().getBackstackTag());
        }

        transaction.replace(containerId, event.getFragment(), event.getFragment().getBackstackTag());
        transaction.commit();
    }

    public final void popBackStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount() - 1; ++i) {
            fm.popBackStackImmediate();
        }
    }
}
