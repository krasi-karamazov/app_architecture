// Generated code from Butter Knife. Do not modify!
package com.kpkdev.android.dev.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ExampleFragment$$ViewBinder<T extends com.kpkdev.android.dev.ui.fragments.ExampleFragment> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558483, "field 'mContainer'");
    target.mContainer = view;
  }

  @Override public void unbind(T target) {
    target.mContainer = null;
  }
}
