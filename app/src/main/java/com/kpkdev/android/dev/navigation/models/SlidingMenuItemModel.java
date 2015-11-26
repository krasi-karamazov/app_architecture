package com.kpkdev.android.dev.navigation.models;

/**
 * Created by krasimir.karamazov on 12/17/2014.
 */
public class SlidingMenuItemModel {
    public static final int NO_ICON = -1;
    private int mIcon;
    private String mLabel;
    private long id = -1;
    private int mStartsActivity;

    public SlidingMenuItemModel() {

    }

    public SlidingMenuItemModel(int icon, String label, int startsActivity) {
        mIcon = icon;
        mLabel = label;
        mStartsActivity = startsActivity;
    }

    public void setIconId(int icon) {
        mIcon = icon;
    }

    public int getIcon() {
        return mIcon;
    }

    public void setLabel(String label) {
        mLabel = label;
    }

    public String getLabel() {
        return mLabel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getStartsActivity() {
        return (mStartsActivity == 1);
    }

    public void setStartsActivity(int startsActivity) {
        this.mStartsActivity = startsActivity;
    }
}
