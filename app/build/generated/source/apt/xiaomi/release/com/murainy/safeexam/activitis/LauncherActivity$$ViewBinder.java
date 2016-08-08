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

public class LauncherActivity$$ViewBinder<T extends LauncherActivity> implements ViewBinder<T> {
  @Override
  public Unbinder bind(final Finder finder, final T target, Object source) {
    InnerUnbinder unbinder = createUnbinder(target);
    View view;
    view = finder.findRequiredView(source, 2131624107, "field 'bnt_me' and method 'next'");
    target.bnt_me = finder.castView(view, 2131624107, "field 'bnt_me'");
    unbinder.view2131624107 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.next(p0);
      }
    });
    view = finder.findRequiredView(source, 2131624111, "field 'bnt_you' and method 'man'");
    target.bnt_you = finder.castView(view, 2131624111, "field 'bnt_you'");
    unbinder.view2131624111 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.man(p0);
      }
    });
    return unbinder;
  }

  protected InnerUnbinder<T> createUnbinder(T target) {
    return new InnerUnbinder(target);
  }

  protected static class InnerUnbinder<T extends LauncherActivity> implements Unbinder {
    private T target;

    View view2131624107;

    View view2131624111;

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
      view2131624107.setOnClickListener(null);
      target.bnt_me = null;
      view2131624111.setOnClickListener(null);
      target.bnt_you = null;
    }
  }
}
