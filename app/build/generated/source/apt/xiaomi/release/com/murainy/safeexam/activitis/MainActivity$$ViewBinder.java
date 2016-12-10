// Generated code from Butter Knife. Do not modify!
package com.murainy.safeexam.activitis;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import butterknife.internal.ViewBinder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class MainActivity$$ViewBinder<T extends MainActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624211, "field 'iv_left' and method 'back'");
    target.iv_left = finder.castView(view, 2131624211, "field 'iv_left'");
    unbinder.view2131624211 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.back(p0);
      }
    });
    view = finder.findRequiredView(source, 2131624216, "field 'iv_right'");
    target.iv_right = finder.castView(view, 2131624216, "field 'iv_right'");
    view = finder.findRequiredView(source, 2131624213, "field 'tv_title'");
    target.tv_title = finder.castView(view, 2131624213, "field 'tv_title'");
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends MainActivity> implements Unbinder {
    private T target;

    View view2131624211;

    protected InnerUnbinder(T target) {
      this.target = target;
    }

    @Override
    public final void unbind() {
      if (target == null) throw new IllegalStateException("Bindings already cleared.");
      unbind(target);
      target = null;
    }

    protected void unbind(T target) {
      view2131624211.setOnClickListener(null);
      target.iv_left = null;
      target.iv_right = null;
      target.tv_title = null;
    }
  }
}
