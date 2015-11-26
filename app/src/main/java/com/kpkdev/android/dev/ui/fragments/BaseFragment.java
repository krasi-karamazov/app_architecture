package com.kpkdev.android.dev.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.kpkdev.android.dev.R;
import com.kpkdev.android.dev.ui.fragments.dialogs.BaseDialog;
import com.kpkdev.android.dev.ui.fragments.dialogs.ProgressDialog;
import com.kpkdev.android.dev.utils.BusProvider;
import com.kpkdev.android.dev.utils.Constants;
import com.kpkdev.android.dev.utils.Logger;
import butterknife.ButterKnife;

/**
 * Created by krasimir.karamazov on 12/17/2014.
 */
public abstract class BaseFragment extends Fragment{

    private String title;
    private SharedPreferences prefs;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        BusProvider.getInstance().register(this);
        prefs = getActivity().getSharedPreferences(Constants.PREFS_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        BusProvider.getInstance().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, rootView);

        initUI();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        if(getTitle() != null) {
            title = getTitle();
        }

        if (getActivity() != null) {
            if (getActivity() instanceof ActionBarActivity) {
                if(title != null) {
                    ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(title);
                }
            }
        }
    }

    protected final void setUpActionBarTitle() {
        try {
            if (getActivity() instanceof ActionBarActivity) {
                /*((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='" + ColorUtils.rgbToHexString(DataHolder.getInstance().getSchoolInfo().getSchoolSecondaryColor()) + "'" + ">" + getTitle() + "</font>"));*/
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        int menuId = getMenuId();
        if(menuId != 0) {
            inflater.inflate(menuId, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return handleMenuItemClick(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(BaseFragment.this);
    }

    protected final void showProgress() {

        try {
            FragmentManager fm = getActivity().getSupportFragmentManager();
            if (fm.findFragmentByTag(ProgressDialog.TAG) == null) {
                Bundle args = new Bundle();
                args.putString(BaseDialog.MESSAGE_ARG_KEY, getString(R.string.please_wait));
                BaseDialog progressDialog = ProgressDialog.getInstance(args);
                progressDialog.show(getActivity().getSupportFragmentManager(), ProgressDialog.TAG);
            }
        } catch (NullPointerException e) {
        }
    }

    protected final void hideProgress() {
        try {
            BaseDialog dialog = (ProgressDialog) getActivity().getSupportFragmentManager().findFragmentByTag(ProgressDialog.TAG);
            if (dialog != null) {
                Logger.d("Dialog dismissed");
                dialog.dismiss();
            }
        } catch (NullPointerException e) {
        }
    }

    public final String getBackstackTag() {
        return getFragmentTag();
    }

    protected abstract void retryQuery();

    protected abstract void initUI();

    protected abstract int getLayoutId();
    protected abstract int getMenuId();

    protected abstract String getTitle();

    protected abstract String getFragmentTag();

    protected abstract boolean handleMenuItemClick(MenuItem item);
}
