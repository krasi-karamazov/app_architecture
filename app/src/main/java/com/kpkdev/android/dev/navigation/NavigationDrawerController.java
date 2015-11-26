package com.kpkdev.android.dev.navigation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import com.kpkdev.android.dev.R;
import com.kpkdev.android.dev.navigation.events.NavigationEvent;
import com.kpkdev.android.dev.navigation.models.SlidingMenuItemModel;
import com.kpkdev.android.dev.navigation.models.SlidingMenuModel;
import com.kpkdev.android.dev.ui.fragments.ExampleFragment;
import com.kpkdev.android.dev.utils.BusProvider;
import com.kpkdev.android.dev.utils.Logger;

import java.util.Random;

/**
 * Created by krasimir.karamazov on 7/7/2015.
 */
public class NavigationDrawerController implements NavigationItemClickListener {
    private static NavigationDrawerController sInstance;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private RecyclerView mDrawerListView;
    private SlidingMenuModel mModel;
    private NavigationAdapter listAdapter;
    private Context mContext;

    public static synchronized NavigationDrawerController getInstance() {
        if(sInstance == null) {
            sInstance = new NavigationDrawerController();
        }

        return sInstance;
    }

    private NavigationDrawerController() {

    }

    public ActionBarDrawerToggle getDrawerToggle(){
        return mDrawerToggle;
    }

    public DrawerLayout getDrawerLayout(){
        return mDrawerLayout;
    }

    public void init(Context context) {
        mContext = context;
        mModel = SlidingMenuModel.getInstance(context);
        ViewGroup view = (ViewGroup)((AppCompatActivity)mContext).getWindow().getDecorView();
        View content = view.getChildAt(0);
        mDrawerListView = (RecyclerView)content.findViewById(R.id.menu_list);
        mDrawerListView.setLayoutManager(new LinearLayoutManager(context));
        listAdapter = new NavigationAdapter(mModel.getData(), this);
        mDrawerListView.setAdapter(listAdapter);
        mDrawerLayout = (DrawerLayout)content.findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = getDrawerListener(mContext);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    public void toggleDrawer(){
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public boolean isDrawerOpened() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    private ActionBarDrawerToggle getDrawerListener(Context context) {
        return new ActionBarDrawerToggle((AppCompatActivity)context, mDrawerLayout, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
    }

    private void generateIntentAndStartActivity(Class activityClass, Bundle args) {
        Intent intent = new Intent(mContext, activityClass);
        if(args != null && args.size() > 0) {
            intent.putExtras(args);
        }
        mContext.startActivity(intent);
    }

    @Override
    public void onNavigationEvent(int index) {
        Logger.d("INDEX " + index);
        SlidingMenuItemModel model = mModel.getItem(index);
        if(model.getStartsActivity()) {
            //start activity like this:
            //generateIntentAndStartActivity(SomeActivity.class, Bundle args)
        }else{

            //Just an example
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            Bundle args = new Bundle();
            args.putInt(ExampleFragment.COLOR_KEY, color);
            BusProvider.getInstance().post(new NavigationEvent(ExampleFragment.getInstance(args), true, true, true, R.id.fl_fragment_container));
        }
        toggleDrawer();
    }
}
