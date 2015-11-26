// Generated code from Butter Knife. Do not modify!
package com.kpkdev.android.dev.navigation;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class NavigationAdapter$ViewHolder$$ViewBinder<T extends com.kpkdev.android.dev.navigation.NavigationAdapter.ViewHolder> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558486, "field 'imgIconView'");
    target.imgIconView = finder.castView(view, 2131558486, "field 'imgIconView'");
    view = finder.findRequiredView(source, 2131558487, "field 'txtView'");
    target.txtView = finder.castView(view, 2131558487, "field 'txtView'");
  }

  @Override public void unbind(T target) {
    target.imgIconView = null;
    target.txtView = null;
  }
}
