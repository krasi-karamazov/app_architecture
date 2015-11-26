package com.kpkdev.android.dev.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.kpkdev.android.dev.R;

import butterknife.Bind;

/**
 * Created by krasimir.karamazov on 7/7/2015.
 */
public class ExampleFragment extends BaseFragment {

    public static final String TAG = ExampleFragment.class.getSimpleName();

    public static final String COLOR_KEY = "color";

    @Bind(R.id.main_container)
    View mContainer;

    int color;

    public static ExampleFragment getInstance(Bundle args) {
        ExampleFragment fragment = new ExampleFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void retryQuery() {

    }

    @Override
    protected void initUI() {
        if(getArguments() != null) {
            color = getArguments().getInt(COLOR_KEY, Color.RED);
        }else{
            color = Color.RED;
        }

        mContainer.setBackgroundColor(color);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_example;
    }

    @Override
    protected int getMenuId() {
        return 0;
    }

    @Override
    protected String getTitle() {
        return TAG;
    }

    @Override
    protected String getFragmentTag() {
        return TAG;
    }

    @Override
    protected boolean handleMenuItemClick(MenuItem item) {
        return false;
    }
}
