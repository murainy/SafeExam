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
    view = finder.findRequiredView(source, 2131624123, "field 'ta' and method 'next'");
    target.ta = finder.castView(view, 2131624123, "field 'ta'");
    unbinder.view2131624123 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.next(p0);
      }
    });
    view = finder.findRequiredView(source, 2131624124, "field 'ts' and method 'setup'");
    target.ts = finder.castView(view, 2131624124, "field 'ts'");
    unbinder.view2131624124 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setup(p0);
      }
    });
    view = finder.findRequiredView(source, 2131624125, "field 'tg' and method 'man'");
    target.tg = finder.castView(view, 2131624125, "field 'tg'");
    unbinder.view2131624125 = view;
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

    View view2131624123;

    View view2131624124;

    View view2131624125;

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
      view2131624123.setOnClickListener(null);
      target.ta = null;
      view2131624124.setOnClickListener(null);
      target.ts = null;
      view2131624125.setOnClickListener(null);
      target.tg = null;
    }
  }
}
